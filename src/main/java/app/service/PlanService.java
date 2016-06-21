package app.service;

import app.dao.PlanDAO;
import app.dao.UserDAO;
import app.entity.PlanEntity;
import app.jsonClass.Plan;
import app.jsonClass.PlanRes;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
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
    public standardRes addPlan(String scheduleId,String travelDate,String plan){
        PlanEntity pe=new PlanEntity(scheduleId,travelDate,plan);
        try{
            planDAO.save(pe);
//            pe.getPlanId();
            // jixiang: 我加了返回id
            return new standardRes(0, pe.getPlanId());
//            return new standardRes(0,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    //删除某天的行程plan
    public standardRes deletePlan(String planId){
        PlanEntity pe=planDAO.findOne(planId);
        if (pe.equals(null)) return new standardRes(602,"plan不存在");
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
        List<Plan> planList=new ArrayList<>();
        for (int i=0;i<pe.size();i++){
            Plan plan=new Plan(pe.get(i).getPlanId(),pe.get(i).getTravelDate(),pe.get(i).getPlan());
            planList.add(plan);
        }
        return new PlanRes(0,planList);
    }

}
