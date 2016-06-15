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

    public boolean checkUserByEmail(String email){
        UserEntity ue = userDAO.findByEmail(email);
        return ue != null;
    }

    public boolean checkUserByName(String name){
        UserEntity ue = userDAO.findByName(name);
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
}
