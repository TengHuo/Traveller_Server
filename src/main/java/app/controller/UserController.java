package app.controller;

import app.jsonClass.standardRes;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhujay on 16/6/15.
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

//    @RequestMapping("/user/{username}/")
//    public standardRes register(){
//
//    }
}
