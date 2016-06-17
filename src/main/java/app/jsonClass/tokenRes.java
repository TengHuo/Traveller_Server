package app.jsonClass;

/**
 * Created by zhujay on 16/6/17.
 */
public class tokenRes {
    private int errCode;
    private String token;

    public tokenRes(int errCode, String token) {
        this.errCode = errCode;
        this.token = token;
    }

    public tokenRes() {
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
