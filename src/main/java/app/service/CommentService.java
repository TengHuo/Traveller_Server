package app.service;

import app.dao.PostDAO;
import app.dao.CommentDAO;
import app.dao.UserDAO;
import app.entity.CommentEntity;
import app.jsonClass.EntitiesRes;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by jixiang on 16/6/15.
 */
@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    UserDAO userDAO;


    public standardRes saveComment(String post_id, String creater_id, String content) {
        java.util.Date create_date = new java.util.Date();
        CommentEntity ce = new CommentEntity(post_id, creater_id, new Date(create_date.getTime()), content);

        if (userDAO.findById(creater_id).equals("[]")) return new standardRes(305, "评论者不存在");

        if (postDAO.findById(post_id).equals("[]")) return new standardRes(306, "post不存在");

        try {
            commentDAO.save(ce);
            return new standardRes(0, "评论成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(304, "发表评论失败\n" + e.toString());
        }
    }

    public EntitiesRes getCommentList(String id) {
        if (commentDAO.findByCreaterId(id).equals(("[]"))) return new EntitiesRes(305, "评论者不存在");
        try {
            return new EntitiesRes(308, commentDAO.findByCreaterId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new EntitiesRes(309, "获取失败");
        }
    }

    public EntitiesRes getCommentPList(String id) {
        System.out.println("post____id: " + id);
        if (commentDAO.findByPostId(id).equals(("[]"))) return new EntitiesRes(306, "post不存在");
        try {
            return new EntitiesRes(308, commentDAO.findByPostId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new EntitiesRes(309, "获取失败");
        }
    }
}
