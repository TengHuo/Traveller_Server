package app.service;

import app.dao.ImageDAO;
import app.dao.PostDAO;
import app.entity.ImageEntity;
import app.entity.PostEntity;
import app.entity.RelationEntity;
import app.jsonClass.PostRes;
import app.jsonClass.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhujay on 16/6/22.
 */
@Service
public class SearchService {
    @Autowired
    PostDAO postDAO;

    @Autowired
    ImageDAO imageDAO;

    public PostRes ketWordQuery(String query){
        try {
            List<Posts> postsList = new ArrayList<>();
            List<PostEntity> result = postDAO.keyWordQuery(query);

            result.forEach(R -> {
                Posts post = new Posts();
                post.setId(R.getId());
                post.setTitle(R.getTitle());
                post.setLocation(R.getLocationDesc());
                post.setSummary(R.getSummary());

                List<ImageEntity> imageEntityList = imageDAO.findByPostId(R.getId());
                if (imageEntityList.size() > 1) {
                    Random random = new Random();
                    int max = imageEntityList.size()-1, min = 0;
                    post.setImageURL(imageEntityList.get(random.nextInt(max)%(max-min+1) + min).getImageUrl());
                } else {
                    post.setImageURL(imageEntityList.get(0).getImageUrl());
                }
                postsList.add(post);
            });
            return new PostRes(0, postsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }
    }
}
