package app.controller;

import app.jsonClass.PostRes;
import app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jixiang on 2016/6/17.
 */

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/post/detail", method = RequestMethod.GET)

    public PostRes submitComment(@RequestParam String token,
                                 @RequestParam String id) {
        if (token.equals("")) return new PostRes(401);
        if (id.equals("")) return new PostRes(402);

        try {
            return postService.getPostById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostRes(999);
        }
    }
}
