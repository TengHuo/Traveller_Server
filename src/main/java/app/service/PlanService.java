package app.service;

import app.dao.PlanDAO;
import app.dao.UserDAO;
import app.entity.PlanEntity;
import app.jsonClass.PlanRes;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tokki on 16/6/19.
 */
@Service
public class PlanService {
    @Autowired
    PlanDAO planDAO;
    @Autowired
    UserDAO userDAO;

    //新增一天的行程plan
    public standardRes addPlan(String scheduleId,String plan){
        PlanEntity pe=new PlanEntity(scheduleId,plan);
        try{
            planDAO.save(pe);
            return new standardRes(0,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    //删除某天的行程plan
    public standardRes deletePlan(String planId){
        PlanEntity pe=planDAO.findOne(planId);
        try{
            planDAO.delete(pe);
            return new standardRes(0,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    //获取一个schedule的全部行程
    public PlanRes getPlan(String scheduleId){

        List<PlanEntity> pe=planDAO.findByScheduleId(scheduleId);
        return new PlanRes(0);
    }

}
