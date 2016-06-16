package app.controller;

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

    public standardRes getCommentList(@RequestParam(value = "token", defaultValue = "") String token,
                                      @RequestParam(value = "id", defaultValue = "") String id) {
        if (token.equals("")) return new standardRes(306, "请求失败");
        if (id.equals("")) return new standardRes(307, "id不能为空");

        try {
            commentService.getCommentList(id);
            return new standardRes(308, "请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }
}