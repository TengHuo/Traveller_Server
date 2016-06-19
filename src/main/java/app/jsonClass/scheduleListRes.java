package app.jsonClass;

import app.entity.ScheduleEntity;

import java.util.List;

/**
 * Created by zhujay on 16/6/19.
 */
public class scheduleListRes {
    private int errCode;
    private String errMessage;
    private List<ScheduleEntity> scheduleList;

    public scheduleListRes(int errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public scheduleListRes() {

    }

    public scheduleListRes(int errCode, String errMessage, List<ScheduleEntity> scheduleList) {

        this.errCode = errCode;
        this.errMessage = errMessage;
        this.scheduleList = scheduleList;
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

    public List<ScheduleEntity> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleEntity> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
