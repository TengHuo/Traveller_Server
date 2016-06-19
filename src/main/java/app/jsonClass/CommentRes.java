package app.jsonClass;

import app.entity.CommentEntity;

import java.util.List;

/**
 * Created by jixiang on 2016/6/16.
 */
public class CommentRes<T> {
    private int errCode;
    private T comments;

    public CommentRes(int errCode) {
        this.errCode = errCode;
        this.comments = null;
    }

    public CommentRes(int errCode, T comments) {
        this.errCode = errCode;
        this.comments = comments;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getComments() {
        return comments;
    }

    public void setComments(T comments) {
        this.comments = comments;
    }

}
