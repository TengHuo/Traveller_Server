package app.service;

import app.dao.ImageDAO;
import app.dao.PostDAO;
import app.entity.ImageEntity;
import app.entity.PostEntity;
import app.jsonClass.Image;
import app.jsonClass.Post;
import app.jsonClass.PostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */

@Service
public class PostService {
    @Autowired
    PostDAO postDAO;

    @Autowired
    ImageDAO imageDAO;

    public PostRes getPostById(String id) {
        if (postDAO.findById(id) == null) return new PostRes(403);
        try {
            List<Image> imageList = new ArrayList<>();
            List<ImageEntity> imageEntityList = imageDAO.findByPostId(id);

            imageEntityList.forEach(imageEntity -> {
                Image image = new Image();
                image.setId(imageEntity.getId());
                image.setImageUrl(imageEntity.getImageUrl());
                imageList.add(image);
            });


//            for (int i = 0; i < length; i++) {
//                Image image = new Image();
//                image.setId(imageEntityList.get(i).getId());
//                image.setImageUrl(imageEntityList.get(i).getImageUrl());
//                imageList.add(image);
//            }

            Post post = new Post();
            PostEntity postEntity = postDAO.findById(id);
            post.setId(postEntity.getId());
            post.setTitle(postEntity.getTitle());
            post.setCreatorId(postEntity.getCreaterId());
            post.setLocationDesc(postEntity.getLocationDesc());
            post.setLatitude(postEntity.getLatitude());
            post.setLongitude(postEntity.getLongitude());
            post.setSummary(postEntity.getSummary());
            post.setImageURLs(imageList);
            post.setCreateDate(postEntity.getCreateDate());

            return new PostRes(404, post);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(304);
        }
    }

}
