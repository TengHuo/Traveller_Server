package app.controller;

import app.jsonClass.standardRes;
import app.jsonClass.tokenRes;
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
        if (password.equals("")) return new tokenRes(202,"密码不能为空");

        try{
            return userService.login(name,password);

        }catch (Exception e){
            e.printStackTrace();
            return new tokenRes(999,e.toString());
        }
    }
}
