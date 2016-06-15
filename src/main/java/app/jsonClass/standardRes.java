package app.jsonClass;

/**
 * Created by zhujay on 16/6/15.
 */
public class standardRes {
    private int errCode;
    private String errMessage;

    public standardRes() {
        this.errCode = 0;
        this.errMessage = "";
    }

    public standardRes(int errCode, String errMessage) {

        this.errCode = errCode;
        this.errMessage = errMessage;
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
}
