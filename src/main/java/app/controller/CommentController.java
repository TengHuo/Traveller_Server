package app.controller;

import app.jsonClass.CommentRes;
import app.jsonClass.standardRes;
import app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jixiang on 16/6/15.
 */

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/submit", method = RequestMethod.POST)

    public standardRes submitComment(@RequestParam String post_id, @RequestParam String creater_id, @RequestParam String content) {
        if (post_id.equals("")) return new standardRes(301, "post id不能为空");
        if (creater_id.equals("")) return new standardRes(302, "creator id不能为空");
        if (content.equals("")) return new standardRes(303, "评论内容不能为空");

        try {
            return commentService.saveComment(post_id, creater_id, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/comment/list", method = RequestMethod.GET)

    public CommentRes getCommentList(@RequestParam(value = "token", defaultValue = "") String token,
                                     @RequestParam(value = "creator_id", defaultValue = "") String creator_id) {
        if (token.equals("")) return new CommentRes(310);
        if (creator_id.equals("")) return new CommentRes(307);

        try {
            return commentService.getCommentList(creator_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(999);
        }
    }

    @RequestMapping(value = "/comment/plist", method = RequestMethod.GET)

    public CommentRes getCommentPList(@RequestParam(value = "token", defaultValue = "") String token,
                                      @RequestParam(value = "post_id", defaultValue = "") String post_id) {
        if (token.equals("")) return new CommentRes(310);
        if (post_id.equals("")) return new CommentRes(307);
        System.out.println("post_id: " + post_id);
        try {
            return commentService.getCommentPList(post_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(999);
        }
    }
}