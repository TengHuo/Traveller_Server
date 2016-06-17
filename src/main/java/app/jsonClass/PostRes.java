package app.jsonClass;

import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */
public class PostRes<T> {
    private int errCode;
    private T post;
    private String token;

    public PostRes(int errCode, T post, String token) {
        this.errCode = errCode;
        this.post = post;
        this.token = token;
    }

    public PostRes(int errCode, String token) {
        this.errCode = errCode;
        this.post = null;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
