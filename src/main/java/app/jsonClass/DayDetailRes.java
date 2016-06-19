package app.jsonClass;

/**
 * Created by jixiang on 2016/6/19.
 */
public class DayDetailRes<T> {
    private int errCode;
    private T details;

    public DayDetailRes(int errCode, T details) {
        this.errCode = errCode;
        this.details = details;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }
}
