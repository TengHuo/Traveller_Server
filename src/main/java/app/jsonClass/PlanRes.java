package app.jsonClass;

import java.util.List;

/**
 * Created by tokki on 16/6/19.
 */
public class PlanRes {
    private int errCode;
    private List<Plan> planList;

    public PlanRes(int errCode,List<Plan> planList){
        this.errCode=errCode;
        this.planList=planList;
    }

    public PlanRes(int errCode){
        this.errCode=errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
