package app.service;

import app.dao.ImageDAO;
import app.dao.PostDAO;
import app.dao.UserDAO;
import app.entity.ImageEntity;
import app.entity.PostEntity;
import app.entity.UserEntity;
import app.jsonClass.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jixiang on 2016/6/17.
 */

@Service
public class PostService {
    @Autowired
    PostDAO postDAO;

    @Autowired
    ImageDAO imageDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenService tokenService;

    public PostRes getPostById(String id, String _id) {
        if (postDAO.findById(id) == null) return new PostRes(403, tokenService.id2token(_id));
        try {
            List<Image> imageList = new ArrayList<>();
            List<ImageEntity> imageEntityList = imageDAO.findByPostId(id);

            imageEntityList.forEach(imageEntity -> {
                Image image = new Image();
                image.setId(imageEntity.getId());
                image.setImageUrl(imageEntity.getImageUrl());
                imageList.add(image);
            });

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

            return new PostRes(404, post, tokenService.id2token(_id));
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, tokenService.id2token(_id));
        }
    }

    public CreatorRes getPostCreator(String post_id, String _id) {
        if (postDAO.findById(post_id) == null) return new CreatorRes(403, tokenService.id2token(_id));
        try {
            Creator creator = new Creator();
            UserEntity userEntity = userDAO.findById(postDAO.findById(post_id).getCreaterId());
            creator.setId(userEntity.getId());
            creator.setName(userEntity.getName());
            creator.setAvatar(userEntity.getAvatar());
            creator.setEmail(userEntity.getEmail());
            creator.setLocation(userEntity.getLocation());
            creator.setGender(userEntity.getGender());
            creator.setSummary(userEntity.getSummary());
            creator.setHomepage(userEntity.getHomepage());
            creator.setRegister_date(userEntity.getRegisterDate());
            creator.setFollowing_num(userEntity.getFollowingNum());
            creator.setFollower_num(userEntity.getFollowerNum());
            return new CreatorRes(404, creator, tokenService.id2token(_id));
        } catch (Exception e) {
            e.printStackTrace();
            return new CreatorRes(405, tokenService.id2token(_id));
        }
    }

    public PostRes getPosts(String id, String _id) {
        if (userDAO.findById(id) == null) return new PostRes(407, tokenService.id2token(_id));
        try {
            List<Posts> postsList = new ArrayList<>();
            List<PostEntity> postEntityList = postDAO.findAllById(id);

            postEntityList.forEach(postEntity -> {
                Posts post = new Posts();
                post.setId(postEntity.getId());
                post.setTitle(postEntity.getTitle());
                post.setLocation(postEntity.getLocationDesc());

                // 随机选一张缩略图
                List<ImageEntity> imageEntityList = imageDAO.findByPostId(postEntity.getId());
                if (imageEntityList.size() > 1) {
                    Random random = new Random();
                    int max = imageEntityList.size(), min = 1;
                    post.setImageURL(imageEntityList.get(random.nextInt(max)%(max-min+1) + min).getImageUrl());
                } else {
                    post.setImageURL(imageEntityList.get(0).getImageUrl());
                }
                postsList.add(post);
            });


            return new PostRes(404, postsList, tokenService.id2token(_id));
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, tokenService.id2token(_id));
        }
    }

    public standardRes savePost(Post post, List<String> imageURLs) {
        if (userDAO.findById(post.getCreatorId()) == null) return new standardRes(407, "用户id不存在");
        try {
            PostEntity pe = new PostEntity(post.getTitle(), post.getCreatorId(), post.getLocationDesc(),
                    post.getLatitude(), post.getLongitude(), post.getSummary(), post.getCreateDate());
            postDAO.save(pe);
            imageURLs.forEach(url -> {
                ImageEntity image = new ImageEntity(pe.getId(), url);
                imageDAO.save(image);
            });
            return new standardRes(0, "post发布成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(304, "post发布失败\n" + e.toString());
        }
    }

}
