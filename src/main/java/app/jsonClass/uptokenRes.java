package app.jsonClass;

/**
 * Created by zhujay on 16/6/17.
 */
public class uptokenRes {
    private int errCode;
    private String errMessage;
    private String uptoken;

    public uptokenRes() {
    }

    public uptokenRes(int errCode, String errMessage, String uptoken) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.uptoken = uptoken;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getUptoken() {
        return uptoken;
    }

    public void setUptoken(String uptoken) {
        this.uptoken = uptoken;
    }
}
