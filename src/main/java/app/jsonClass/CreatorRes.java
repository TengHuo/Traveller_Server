package app.jsonClass;

/**
 * Created by LENOVO on 2016/6/17.
 */
public class CreatorRes {
    private int errCode;
    private Creator creator;
    private String token;

    public CreatorRes(int errCode, Creator creator, String token) {
        this.errCode = errCode;
        this.creator = creator;
        this.token = token;
    }

    public CreatorRes(int errCode, String token) {
        this.errCode = errCode;
        this.token = token;
    }
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
