package app.service;

import app.dao.PostDAO;
import app.entity.PostEntity;
import app.jsonClass.Post;
import app.jsonClass.PostRes;
import app.util.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiang on 2016/6/21.
 */
@Service
public class AMAPService {
    @Autowired
    PostDAO postDAO;

    public PostRes getNearbyPosts(Double leftLongitude, Double rightLongitude, Double topLatitude, Double bottomLatitude) {
        try {
            List<PostEntity> postEntityList = postDAO.findAll();
            List<PostEntity> finalResult = new ArrayList<>();
            int postInDB = 0;
            for (int i = 0; i < postEntityList.size(); i++) {
                if (postEntityList.get(i).getLatitude() > bottomLatitude && postEntityList.get(i).getLatitude() < topLatitude
                        && postEntityList.get(i).getLongitude() > leftLongitude && postEntityList.get(i).getLongitude() > rightLongitude) {
                    postInDB += 1;
                    finalResult.add(postEntityList.get(i));
                }
            }
            List<PostEntity> finalList = new ArrayList<>();
            if (postInDB < 10) {
                List<PostEntity> postEntities = getAMAPPosts(
                        (topLatitude + bottomLatitude) / 2,
                        (leftLongitude + rightLongitude) / 2);
                postEntities.forEach(post -> {
                    if (postDAO.findByTitle(post.getTitle()).size() == 0) {
                        postDAO.save(post);
                        finalList.add(post);
                    } else {
                        if (postDAO.findByTitle(post.getTitle()).size() >= 1) {
                            finalList.addAll(postDAO.findByTitle(post.getTitle()));
                        }
                    }
                });
                finalResult.addAll(finalList);
            }
            return new PostRes(0, finalResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }

    }

    public List<PostEntity> getAMAPPosts(double latitude, double longitude) {
        final String KEY = "a603725fa4a9486ecfcb2116cb556c84";
        final String OUTPUT = "xml";
        final String RADIUS = "10000";
        final String TYPES = "110000";

        String lati = Double.toString(latitude);
        String longi = Double.toString(longitude);
        String location = lati + "," + longi;
        String params = "key=" + KEY + "&location=" + location + "&output=" + OUTPUT +
                "&radius=" + RADIUS + "&types=" + TYPES;
        Document document = HTTPUtil.sendGet("http://restapi.amap.com/v3/place/around", params);
        if (document != null) {
            List<PostEntity> postEntityList = new ArrayList<>();
            NodeList node = document.getElementsByTagName("poi");
            java.util.Date create_date = new java.util.Date();

            for (int i = 0; i < node.getLength(); i++) {
                PostEntity postEntity = new PostEntity();

//                postEntity.setId(node.item(i).getChildNodes().item(0).getTextContent());
                postEntity.setTitle(node.item(i).getChildNodes().item(1).getTextContent());
                postEntity.setCreaterId("e5d0351a-9092-43f1-b187-cfe64cda2ef8");
                postEntity.setLocationDesc(node.item(i).getChildNodes().item(5).getTextContent());
                postEntity.setLongitude(Double.parseDouble(node.item(i).getChildNodes().item(6).getTextContent().split(",")[0]));
                postEntity.setLatitude(Double.parseDouble(node.item(i).getChildNodes().item(6).getTextContent().split(",")[1]));
                postEntity.setSummary(node.item(i).getChildNodes().item(2).getTextContent());
                postEntity.setCreateDate(new Date(create_date.getTime()));
                postEntityList.add(postEntity);
            }
            return postEntityList;
        }
        return null;
    }
}
