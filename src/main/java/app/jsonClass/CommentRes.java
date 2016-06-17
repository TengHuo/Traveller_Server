package app.jsonClass;

import app.entity.CommentEntity;

import java.util.List;

/**
 * Created by jixiang on 2016/6/16.
 */
public class CommentRes {
    private int errCode;
    private List<Comment> comments;

    public CommentRes() {
        this.errCode = 0;
        this.comments = null;
    }

    public CommentRes(int errCode) {
        this.errCode = errCode;
        this.comments = null;
    }

    public CommentRes(int errCode, List<Comment> comments) {
        this.errCode = errCode;
        this.comments = comments;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
