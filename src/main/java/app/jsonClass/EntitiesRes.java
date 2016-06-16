package app.jsonClass;

import java.util.List;

/**
 * Created by jixiang on 2016/6/16.
 */
public class EntitiesRes<T> {
    private int errCode;
    private T response;

    public EntitiesRes() {
        this.errCode = 0;
        this.response = null;
    }

    public EntitiesRes(int errCode, T response) {

        this.errCode = errCode;
        this.response = response;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
