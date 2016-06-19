package app.controller;

import app.jsonClass.standardRes;
import app.service.ScheduleService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhujay on 16/6/19.
 */
@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/schedule/{token}/{userid}/newschedule",method = RequestMethod.POST)
    public standardRes createSchedule(@PathVariable("token")String token,
                                      @PathVariable("userid")String userid,
                                      @RequestParam(value = "destination",defaultValue = "")String destination,
                                      @RequestParam(value = "scheduleDate",defaultValue = "")String scheduleDate,
                                      @RequestParam(value = "image_url",defaultValue = "")String imageUrl){
        String _id = tokenService.token2id(token);
        if(_id == null | !_id.equals(userid)) return new standardRes(105,"token异常");
        if(destination.equals("")) return new standardRes(501,"destination不能为空");
        if(scheduleDate.equals("")) return new standardRes(502,"scheduleDate不能为空");
        if(imageUrl.equals("")) imageUrl = null;
        try{
            return scheduleService.createSchedule(userid,destination,scheduleDate,imageUrl);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }
}
