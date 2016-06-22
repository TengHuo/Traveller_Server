package app.service;

import app.dao.*;
import app.entity.*;
import app.jsonClass.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Autowired
    RelationDAO relationDAO;

    @Autowired
    CommentDAO commentDAO;

    public PostRes getPostById(String id) {
        if (postDAO.findById(id) == null) return new PostRes(403, "post id不存在");
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

            return new PostRes(0, post);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }
    }

    public PostRes getPostCreator(String post_id) {
        if (postDAO.findById(post_id) == null) return new PostRes(403, "post id不存在");
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
            return new PostRes(0, creator);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }
    }

    public PostRes getPosts(String id) {
        if (userDAO.findById(id) == null) return new PostRes(407, "用户id不存在");
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
                    int max = imageEntityList.size() - 1, min = 0;
                    post.setImageURL(imageEntityList.get(random.nextInt(max)%(max-min+1) + min).getImageUrl());
                }
                if (imageEntityList.size() == 1){
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

    public standardRes savePost(String title, String creatorId, String locationDesc, double latitude, double longitude,
                                String  summary, Date createDate, String  imageURL) {
        if (userDAO.findById(creatorId) == null) return new standardRes(407, "用户id不存在");
        try {
            PostEntity pe = new PostEntity(title, creatorId, locationDesc,
                    latitude, longitude, summary, createDate);
            postDAO.save(pe);

            ImageEntity image = new ImageEntity(pe.getId(), imageURL);
            imageDAO.save(image);

            return new standardRes(0, "post发布成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(304, "post发布失败\n" + e.toString());
        }
    }

    public PostRes getWatchingPosts(String user_id) {
        if (userDAO.findById(user_id) == null) return new PostRes(407, "用户id不存在");
        try {
            List<Posts> postsList = new ArrayList<>();
            List<RelationEntity> re = relationDAO.findByFollowerId(user_id);

            re.forEach(following -> {
                // 查找用户关注的人的post列表
                List<PostEntity> followingPostList = postDAO.findAllById(following.getFolloweeId());

                // 取出用户关注的人的post列表
                followingPostList.forEach(postEntity -> {

                    // 组装每个post
                    Posts post = new Posts();
                    post.setId(postEntity.getId());
                    post.setTitle(postEntity.getTitle());
                    post.setLocation(postEntity.getLocationDesc());
                    post.setSummary(postEntity.getSummary());
                    // 随机选一张缩略图
                    List<ImageEntity> imageEntityList = imageDAO.findByPostId(postEntity.getId());
                    System.out.println(imageEntityList.size());
                    if (imageEntityList.size() > 1) {
                        Random random = new Random();
                        int max = imageEntityList.size()-1, min = 0;
                        post.setImageURL(imageEntityList.get(random.nextInt(max)%(max-min+1) + min).getImageUrl());
                    } else {
                        if(imageEntityList.size()==0) post.setImageURL("");
                        else post.setImageURL(imageEntityList.get(0).getImageUrl());
                    }
                    postsList.add(post);
                });
            });
            return new PostRes(0, postsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }
    }

    public PostRes getPostByCommentId(String comment_id) {
        if (commentDAO.findByCommentId(comment_id) == null) return new PostRes(409, "comment id不存在");
        try {
            CommentEntity commentEntity = commentDAO.findByCommentId(comment_id);
            if (postDAO.findById(commentEntity.getPostId()) == null) return new PostRes(403, "post id不存在");
            PostEntity postEntity = postDAO.findById(commentEntity.getPostId());

            List<Image> imageList = new ArrayList<>();
            List<ImageEntity> imageEntityList = imageDAO.findByPostId(commentEntity.getPostId());

            imageEntityList.forEach(imageEntity -> {
                Image image = new Image();
                image.setId(imageEntity.getId());
                image.setImageUrl(imageEntity.getImageUrl());
                imageList.add(image);
            });

            Post post = new Post();
            post.setId(postEntity.getId());
            post.setTitle(postEntity.getTitle());
            post.setCreatorId(postEntity.getCreaterId());
            post.setLocationDesc(postEntity.getLocationDesc());
            post.setLatitude(postEntity.getLatitude());
            post.setLongitude(postEntity.getLongitude());
            post.setSummary(postEntity.getSummary());
            post.setImageURLs(imageList);
            post.setCreateDate(postEntity.getCreateDate());

            return new PostRes(0, post);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(405, "获取失败");
        }
     }

    public standardRes addImage(String post_id, String imageUrl) {
        if(postDAO.findById(post_id) == null) return new standardRes(402, "post id不能存在");
        try {
            ImageEntity ie = new ImageEntity();
//            ie.setId("id");
            ie.setPostId(post_id);
            ie.setImageUrl(imageUrl);
            imageDAO.save(ie);
            return new standardRes(0, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(405, "获取失败");
        }
    }

}
