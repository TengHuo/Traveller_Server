package app.jsonClass;

import app.entity.CommentEntity;

import java.util.List;

/**
 * Created by jixiang on 2016/6/16.
 */
public class CommentRes {
    private int errCode;
    private List<Comment> comments;
    private String token;

    public CommentRes(int errCode, String token) {
        this.errCode = errCode;
        this.comments = null;
        this.token = token;
    }

    public CommentRes(int errCode, List<Comment> comments, String token) {
        this.errCode = errCode;
        this.comments = comments;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
