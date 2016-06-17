package app.service;

import app.dao.UserDAO;
import app.entity.UserEntity;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by zhujay on 16/6/15.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenService tokenService;

    public boolean checkUserByEmail(String email){
        UserEntity ue = userDAO.findByEmail(email);
        return ue != null;
    }

    public boolean checkUserByName(String name){
        UserEntity ue = userDAO.findByName(name);
        return ue != null;
    }

//    private  boolean checkUserByNameAndPassword(String name,String password){
//        UserEntity ue = userDAO.findByName(name);
//        return ue != null&&ue.getPassword().equals(password);
//    }

    public boolean checkUserById(String id){
        UserEntity ue = userDAO.findById(id);
        return ue != null;
    }

    public standardRes register(String name, String email,String password){
        java.util.Date d = new java.util.Date();
        UserEntity ue = new UserEntity(name,null,email,null,1,null,null,new Date(d.getTime()),0,0,password);
        if(checkUserByEmail(email)) return new standardRes(101,"该邮箱已被注册");

        if(checkUserByName(name)) return new standardRes(102,"该用户名已被注册");

        try{
            userDAO.save(ue);
            return new standardRes();
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    public standardRes login(String name,String password){
        UserEntity ue = userDAO.findByName(name);
        if (ue == null) return new standardRes(201,"登录失败");
        if (ue.getPassword().equals(password)){
            if(tokenService.id2token(ue.getId()) != null)
                return (tokenService.updateToken(ue.getId())) ? new standardRes(0,"登录成功") : new standardRes(999,"更新token失败,登录失败");
            else
                return (tokenService.createToken(ue.getId())) ? new standardRes(0,"登录成功") : new standardRes(999,"创建token失败,登录失败");
        }else{
            return new standardRes(201,"登陆失败");
        }

    }
}
