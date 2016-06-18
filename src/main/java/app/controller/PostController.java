package app.controller;

import app.jsonClass.CreatorRes;
import app.jsonClass.Post;
import app.jsonClass.PostRes;
import app.jsonClass.standardRes;
import app.service.PostService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/post/detail", method = RequestMethod.GET)

    public PostRes getPostDetail(@RequestParam String token,
                                 @RequestParam String id) {

        String _id = tokenService.token2id(token);
        if(_id == null) return new PostRes(105, tokenService.id2token(_id));
        if (id.equals("")) return new PostRes(402, tokenService.id2token(_id));

        try {
            return postService.getPostById(id, _id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, tokenService.id2token(_id));
        }
    }

    @RequestMapping(value = "/post/creator", method = RequestMethod.GET)

    public CreatorRes getPostCreator(@RequestParam String token,
                                     @RequestParam String id) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new CreatorRes(105, tokenService.id2token(_id));
        if (id == null) return new CreatorRes(402, tokenService.id2token(_id));

        try {
            return postService.getPostCreator(id, _id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CreatorRes(999, tokenService.id2token(_id));
        }
    }

    @RequestMapping(value = "/post/posts", method = RequestMethod.GET)

    public PostRes getPosts(@RequestParam String token,
                            @RequestParam String id) {
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(id)) return new PostRes(105, tokenService.id2token(_id));
        if (id == null) return new PostRes(406, tokenService.id2token(_id));

        try {
            return postService.getPosts(id, _id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999, tokenService.id2token(_id));
        }
    }

    @RequestMapping(value = "/post/new", method = RequestMethod.POST)

    public standardRes createPost(@RequestParam String token,
                                  @RequestParam Post post,
                                  @RequestParam List<String> imageURLs) {
        String _id = tokenService.token2id(token);
        if (post.getCreatorId() == null) return new standardRes(406, "用户id不能为空");
        if(_id == null || !_id.equals(post.getCreatorId())) return new standardRes(105, "Token异常");
        if (post.getTitle() == null
                || post.getLocationDesc() == null
                || post.getSummary() == null
                || post.getCreateDate() == null
                || imageURLs.size() < 1) {
            return new standardRes(105, "参数不能有空值");
        }
        try {
            return postService.savePost(post, imageURLs);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, tokenService.id2token(_id));
        }
    }
}
