package app.service;

import app.dao.PostDAO;
import app.dao.CommentDAO;
import app.dao.UserDAO;
import app.entity.CommentEntity;
import app.jsonClass.Comment;
import app.jsonClass.CommentRes;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    TokenService tokenService;


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

    public CommentRes getCommentList(String id, String _id) {
        if (commentDAO.findByCreaterId(id).equals(("[]"))) return new CommentRes(305, tokenService.id2token(_id));
        try {
            List<CommentEntity> commentEntityList = commentDAO.findByCreaterId(id);
            List<Comment> commentList = new ArrayList<>();
            int length = commentEntityList.size();
            for (int i = 0; i< length; i++) {
                Comment comment = new Comment();
                comment.setId(commentEntityList.get(i).getCommentId());
                comment.setCreatorId(commentEntityList.get(i).getCreaterId());
                comment.setCreatorAvatar(userDAO.findById(id).getAvatar());
                comment.setPostId(commentEntityList.get(i).getPostId());
                comment.setContent(commentEntityList.get(i).getContent());
                comment.setCreateDate(commentEntityList.get(i).getCreateDate());
                commentList.add(comment);
            }
            return new CommentRes(0, commentList, tokenService.id2token(_id));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(309, tokenService.id2token(_id));
        }
    }

    public CommentRes getCommentPList(String id, String _id) {
        if (commentDAO.findByPostId(id).equals(("[]"))) return new CommentRes(306, tokenService.id2token(_id));
        try {
            List<CommentEntity> commentEntityList = commentDAO.findByPostId(id);
            List commentList = new ArrayList<>();
            int length = commentEntityList.size();
            for (int i = 0; i< length; i++) {
                Comment comment = new Comment();
                comment.setId(commentEntityList.get(i).getCommentId());
                comment.setCreatorId(commentEntityList.get(i).getCreaterId());
                comment.setCreatorAvatar(userDAO.findById(commentEntityList.get(i).getCreaterId()).getAvatar());
                comment.setPostId(commentEntityList.get(i).getPostId());
                comment.setContent(commentEntityList.get(i).getContent());
                comment.setCreateDate(commentEntityList.get(i).getCreateDate());
                commentList.add(comment);
            }
            return new CommentRes(0, tokenService.id2token(_id));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentRes(309, tokenService.id2token(_id));
        }
    }
}
