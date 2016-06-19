package app.controller;

import app.jsonClass.CommentRes;
import app.jsonClass.standardRes;
import app.service.CommentService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jixiang on 16/6/15.
 */

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/comment/submit", method = RequestMethod.POST)

    public standardRes submitComment(@RequestParam String token,
                                     @RequestParam String post_id,
                                     @RequestParam String creator_id,
                                     @RequestParam String content) {
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(creator_id)) return new standardRes(105,"token异常");

        if (post_id.equals("")) return new standardRes(301, "post id不能为空");
        if (creator_id.equals("")) return new standardRes(302, "creator id不能为空");
        if (content.equals("")) return new standardRes(303, "评论内容不能为空");

        try {
            return commentService.saveComment(post_id, creator_id, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/comment/list", method = RequestMethod.GET)

    public CommentRes getCommentList(@RequestParam(value = "token") String token,
                                     @RequestParam(value = "creator_id") String creator_id) {

        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(creator_id)) return new CommentRes(105, "token异常");
        if (creator_id.equals("")) return new CommentRes(307, "id不能为空");

        try {
            return commentService.getCommentList(creator_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/comment/plist", method = RequestMethod.GET)

    public CommentRes getCommentPList(@RequestParam(value = "token") String token,
                                      @RequestParam(value = "post_id") String post_id) {

        String _id = tokenService.token2id(token);
        if(_id == null) return new CommentRes(105, "token异常");

        if (token.equals("")) return new CommentRes(310, "请求失败");
        if (post_id.equals("")) return new CommentRes(307, "id不能为空");
        System.out.println("post_id: " + post_id);
        try {
            return commentService.getCommentPList(post_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(999, e.toString());
        }
    }
}