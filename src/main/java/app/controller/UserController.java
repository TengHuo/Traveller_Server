package app.controller;

import app.jsonClass.standardRes;
import app.jsonClass.tokenRes;
import app.jsonClass.userInfoRes;
import app.service.TokenService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zhujay on 16/6/15.
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/user/register/{name}",method = RequestMethod.POST)
    public standardRes register(@PathVariable("name")String name,
                                @RequestParam(value = "password",defaultValue = "")String password,
                                @RequestParam(value = "email",defaultValue = "")String email){
        if(password.equals("")) return new standardRes(103,"密码不能为空");
        if(email.equals("")) return new standardRes(104,"email不能为空");

        try {
            return userService.register(name,email,password);
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }

    }

    @RequestMapping(value = "/user/login/{name}",method = RequestMethod.POST)
    public tokenRes login(@PathVariable("name")String name,
                          @RequestParam(value = "password",defaultValue="")String password){
        if (password.equals("")) return new tokenRes(202,"","密码不能为空");

        try{
            return userService.login(name,password);

        }catch (Exception e){
            e.printStackTrace();
            return new tokenRes(999,null,e.toString());
        }
    }

    @RequestMapping(value = "/user/{userid}/info",method = RequestMethod.GET)
    public userInfoRes getUserInfo(@PathVariable("userid") String userid,
                                   @RequestParam(value = "token",defaultValue = "")String token){
        if(tokenService.token2id(token) == null ||!tokenService.token2id(token).equals(userid)) return new userInfoRes(105,"token异常",null);

        return userService.getUserInfo(userid);

    }

    @RequestMapping(value = "/user/info/update", method = RequestMethod.PUT)
    public standardRes updateUserInfo(@RequestParam("token") String token,
                                      @RequestParam("userid") String userid,
                                      @RequestParam("avatar") String avatar,
                                      @RequestParam("location") String location,
                                      @RequestParam("gender") int gender,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("homepage") String homepage) {
        if(tokenService.token2id(token) == null ||!tokenService.token2id(token).equals(userid)) return new standardRes(105,"token异常");
        try {
            return userService.updateUserInfo(userid, avatar, location, gender, summary, homepage);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }
}
