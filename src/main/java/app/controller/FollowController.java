package app.controller;

import app.dao.UserDAO;
import app.jsonClass.standardRes;
import app.service.FollowService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tokki on 16/6/16.
 */
@RestController
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/user/{token}/{following_id}/follow/{followee_id}",method = RequestMethod.POST)
    public standardRes follow(@PathVariable("followee_id")String id,
                              @PathVariable("token")String token,
                              @PathVariable("following_id")String following_id){
//        if (following_id.equals("")) return new standardRes(303,"被follow的人ID不能为空");
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(following_id)) return new standardRes(105,"token异常");
        try {
            return followService.follow(id,following_id);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    @RequestMapping(value="/user/{token}/{following_id}/follow/{followee_id}",method = RequestMethod.DELETE)
    public standardRes cancelFollow(@PathVariable("followee_id")String id,
                                    @PathVariable("token")String token,
                                    @PathVariable("following_id")String following_id){
//        if (following_id.equals("")) return new standardRes(303,"被follow的人ID不能为空");
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(following_id)) return new standardRes(105,"token异常");
        try {
            return followService.cancelFollow(id,following_id);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }
}
