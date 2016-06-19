package app.jsonClass;

import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */
public class PostRes<T> {
    private int errCode;
    private T post;

    public PostRes(int errCode, T post) {
        this.errCode = errCode;
        this.post = post;
    }

    public PostRes(int errCode) {
        this.errCode = errCode;
        this.post = null;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getPost() {
        return post;
    }

    public void setPost(T post) {
        this.post = post;
    }

}
