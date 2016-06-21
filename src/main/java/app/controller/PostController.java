package app.controller;

import app.jsonClass.Post;
import app.jsonClass.PostRes;
import app.jsonClass.standardRes;
import app.service.AMAPService;
import app.service.PostService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */

@RestController
public class PostController {

    @Autowired
    TokenService tokenService;

    @Autowired
    PostService postService;

    @Autowired
    AMAPService amapService;

    @RequestMapping(value = "/post/detail", method = RequestMethod.GET)

    public PostRes getPostDetail(@RequestParam String token,
                                 @RequestParam String id) {

        String _id = tokenService.token2id(token);
        if(_id == null) return new PostRes(105, "token异常");
        if (id.equals("")) return new PostRes(402, "post id不能为空");

        try {
            return postService.getPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/post/creator", method = RequestMethod.GET)

    public PostRes getPostCreator(@RequestParam String token,
                                     @RequestParam String id) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new PostRes(105, "token异常");
        if (id == null) return new PostRes(402, "post id不能为空");

        try {
            return postService.getPostCreator(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/post/posts", method = RequestMethod.GET)

    public PostRes getPosts(@RequestParam String token,
                            @RequestParam String id) {
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(id)) return new PostRes(105, "token异常");
        if (id == null) return new PostRes(406, "用户id不能为空");

        try {
            return postService.getPosts(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/post/new", method = RequestMethod.POST)

    public standardRes createPost(@RequestParam String token,
                                  @RequestParam String title,
                                  @RequestParam String creatorId,
                                  @RequestParam String locationDesc,
                                  @RequestParam double latitude,
                                  @RequestParam double longitude,
                                  @RequestParam String summary,
                                  @RequestParam String createDate,
                                  @RequestParam String imageURL) {
        String _id = tokenService.token2id(token);
        if (creatorId == "" || creatorId.equals(null)) return new standardRes(406, "用户id不能为空");
        if(_id == null || !_id.equals(creatorId)) return new standardRes(105, "Token异常");
        if (title == "" || title.equals(null)
                || locationDesc == "" || locationDesc.equals(null)
                || summary == "" || summary.equals(null)
                || createDate == "" || createDate.equals(null)
                || imageURL == "" || imageURL.equals(null)) {
            return new standardRes(105, "参数不能有空值");
        }
        try {
            return postService.savePost(title, creatorId, locationDesc, latitude, longitude, summary, java.sql.Date.valueOf(createDate), imageURL);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, tokenService.id2token(_id));
        }
    }

    @RequestMapping(value = "/post/watching", method = RequestMethod.GET)

    public PostRes getWatchingPosts(@RequestParam String token,
                                    @RequestParam String user_id) {
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(user_id)) return new PostRes(105, "token异常");
        if (user_id == null) return new PostRes(406, "用户id不能为空");
        try {
            return postService.getWatchingPosts(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }


    // 根据评论id获取post
    @RequestMapping(value = "/post/comment/{comment_id}", method = RequestMethod.GET)
    public PostRes getPostByCommentId(@PathVariable String comment_id,
                                      @RequestParam String token) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new PostRes(105, "token异常");
        if (comment_id == null) return new PostRes(402, "post id不能为空");

        try{
            return postService.getPostByCommentId(comment_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/post/nearby", method = RequestMethod.POST)
    public PostRes getNearByPosts(@RequestParam(value = "token") String token,
                                  @RequestParam(value = "leftLongitude") double leftLongitude,
                                  @RequestParam(value = "rightLongitude") double rightLongitude,
                                  @RequestParam(value = "topLatitude") double topLatitude,
                                  @RequestParam(value = "bottomLatitude") double bottomLatitude) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new PostRes(105, "token异常");
        try {
            return amapService.getNearbyPosts(leftLongitude, rightLongitude, topLatitude, bottomLatitude);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/post/addImage", method = RequestMethod.POST)
    public standardRes addImage(@RequestParam(value = "token") String token,
                                @RequestParam(value = "post_id") String post_id,
                                @RequestParam(value = "imageUrl") String imageUrl) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new standardRes(105, "token异常");
        try {
            return postService.addImage(post_id, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }
}
