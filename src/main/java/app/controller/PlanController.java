package app.controller;

import app.jsonClass.PlanRes;
import app.jsonClass.standardRes;
import app.service.PlanService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by tokki on 16/6/19.
 */
@RestController
public class PlanController {
    @Autowired
    PlanService planService;
    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/user/plan/{token}/{id}",method = RequestMethod.POST)
    public standardRes addPlan(@PathVariable("token")String token,
                               @PathVariable("id")String userid,
                               @RequestParam(value="plan",defaultValue="")String plan,
                               @RequestParam(value = "scheduleId",defaultValue = "")String scheduleId,
                               @RequestParam(value = "travelDate",defaultValue = "")String travelDate){
        String _id=tokenService.token2id(token);
        if (_id==null||!_id.equals(userid)) return new standardRes(105, "token出错");
        if (plan.equals("")||scheduleId.equals("")||travelDate.equals("")) return new standardRes(601,"参数不正确");
        try{
            return planService.addPlan(scheduleId,plan,travelDate);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    @RequestMapping(value = "/user/plan/{token}/{id}/{planId}",method = RequestMethod.DELETE)
    public standardRes deletePlan(@PathVariable("token")String token,
                               @PathVariable("id")String userid,
                               @PathVariable("planId")String planId){
        String _id=tokenService.token2id(token);
        if (_id==null||!_id.equals(userid)) return new standardRes(105, "token出错");
        if (planId.equals("")) return new standardRes(601,"参数不正确");
        try{
            return planService.deletePlan(planId);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    @RequestMapping(value = "/user/plan/{token}/{id}/{scheduleId}",method = RequestMethod.GET)
    public PlanRes getPlan(@PathVariable("token")String token,
                           @PathVariable("id")String userid,
                           @PathVariable("scheduleId")String scheduleId){
        String _id=tokenService.token2id(token);
        if (_id==null||!_id.equals(userid)) return new PlanRes(105);
        if (scheduleId.equals("")) return new PlanRes(601);
        try{
            return planService.getPlan(scheduleId);
        }catch (Exception e){
            e.printStackTrace();
            return new PlanRes(999);
        }
    }

}
