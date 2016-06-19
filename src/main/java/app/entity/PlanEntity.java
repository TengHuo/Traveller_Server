package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by zhujay on 16/6/19.
 */
@Entity
@Table(name = "plan", schema = "traveller", catalog = "")
public class PlanEntity {
    @Id
    @GeneratedValue(generator = "planIdGenerator")
    @GenericGenerator(name = "planIdGenerator",strategy = "uuid2")
    @Column(name = "plan_id")
    private String planId;
    private String scheduleId;
    private Date travelDate;
    private String plan;

    public PlanEntity(String scheduleId,String plan){
        this.scheduleId=scheduleId;
//        this.travelDate=travelDate;
        this.plan=plan;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "schedule_id")
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "travel_date")
    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    @Basic
    @Column(name = "plan")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanEntity that = (PlanEntity) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (travelDate != null ? !travelDate.equals(that.travelDate) : that.travelDate != null) return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (travelDate != null ? travelDate.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        return result;
    }
}
