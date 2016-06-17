package app.jsonClass;

/**
 * Created by zhujay on 16/6/17.
 */
public class tokenRes {
    private int errCode;
    private String userId;
    private String token;


    public tokenRes(int errCode, String userId, String token) {
        this.errCode = errCode;
        this.userId = userId;
        this.token = token;
    }

    public tokenRes() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
