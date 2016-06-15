package app.controller;

import app.jsonClass.hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhujay on 16/6/15.
 */
@RestController
public class controllerDemo {
    @RequestMapping("hello")
    public hello sayhello(@RequestParam(value = "name",defaultValue = "")String name){
        if(name.equals("")) return new hello();

        return new hello(name);
    }
}
