package app.jsonClass;

import java.sql.Date;

/**
 * Created by tokki on 16/6/19.
 */
public class Plan {
    private String plan_id;
    private String travel_date;
    private String plan;

    public Plan(String plan_id,String travel_date,String plan){
        this.plan_id=plan_id;
        this.travel_date=travel_date;
        this.plan=plan;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }
}
