package app.jsonClass;

import java.util.List;

/**
 * Created by jixiang on 2016/6/17.
 */
public class PostRes {
    private int errCode;
    private Post post;

    public PostRes(int errCode, Post post) {
        this.errCode = errCode;
        this.post = post;
    }

    public PostRes(int errCode) {
        this.errCode = errCode;
        this.post = null;
    }

    public PostRes() {
        this.errCode = 0;
        this.post = null;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
