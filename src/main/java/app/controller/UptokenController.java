package app.controller;

import app.jsonClass.uptokenRes;
import app.service.QiniuService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhujay on 16/6/17.
 */
@RestController
public class UptokenController {
    @Autowired
    QiniuService qiniuService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/{token}/uptoken",method = RequestMethod.GET)
    public uptokenRes getUptoken(@PathVariable("token") String token){
        return (tokenService.token2id(token) != null)
                ? (new uptokenRes(0,null,qiniuService.getUpToken()))
                : (new uptokenRes(105,"token异常",null));
    }
}
