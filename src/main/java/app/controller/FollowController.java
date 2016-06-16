package app.controller;

import app.dao.UserDAO;
import app.jsonClass.standardRes;
import app.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tokki on 16/6/16.
 */
@RestController
public class FollowController {
    @Autowired
    FollowService followService;


    @RequestMapping(value = "/user/follow/{id}",method = RequestMethod.POST)
    public standardRes follow(@PathVariable("id")String id,
                              @RequestParam(value="following_id",defaultValue = "")String following_id){
        if (following_id.equals("")) return new standardRes(303,"被follow的人ID不能为空");
        try {
            return followService.follow(id,following_id);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }


}
