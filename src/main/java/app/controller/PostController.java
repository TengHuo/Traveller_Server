package app.controller;

import app.jsonClass.CreatorRes;
import app.jsonClass.PostRes;
import app.service.PostService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
