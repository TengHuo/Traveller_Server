package app.controller;

import app.dao.UserDAO;
import app.jsonClass.follower_ListRes;
import app.jsonClass.following_ListRes;
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
    public standardRes follow(@PathVariable("followee_id")String followee_id,
                              @PathVariable("token")String token,
                              @PathVariable("following_id")String id){
//        if (following_id.equals("")) return new standardRes(303,"被follow的人ID不能为空");
        String _id = tokenService.token2id(token);
        if(_id == null || !_id.equals(id)) return new standardRes(105,id.toString());
        try {
            return followService.follow(id,followee_id);
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
            return followService.cancelFollow(following_id,id);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    @RequestMapping(value = "/user/{token}/{id}/following",method = RequestMethod.GET)
    public following_ListRes getFollowing(@PathVariable("token")String token,
                                          @PathVariable("id")String id){
        String _id=tokenService.token2id(token);
        if (_id==null||!_id.equals(id)) return new following_ListRes(105);
        try{
            return followService.getFollowing(id);
        }catch (Exception e){
            e.printStackTrace();
            return new following_ListRes(999);
        }
    }

    @RequestMapping(value = "/user/{token}/{id}/follower",method = RequestMethod.GET)
    public follower_ListRes getFollower(@PathVariable("token")String token,
                                         @PathVariable("id")String id){
        String _id=tokenService.token2id(token);
        if (_id==null||!_id.equals(id)) return new follower_ListRes(105);
        try{
            return followService.getFollower(id);
        }catch (Exception e){
            e.printStackTrace();
            return new follower_ListRes(999);
        }
    }
}
