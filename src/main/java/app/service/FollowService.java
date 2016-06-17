package app.service;

import app.dao.RelationDAO;
import app.dao.UserDAO;
import app.entity.RelationEntity;
import app.entity.UserEntity;
import app.jsonClass.follower_ListRes;
import app.jsonClass.following_ListRes;
import app.jsonClass.standardRes;
import app.jsonClass.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokki on 16/6/16.
 */
@Service
public class FollowService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    RelationDAO relationDAO;

    //检查用户是否存在
    public boolean checkId(String id){
        UserEntity ue = userDAO.findById(id);
        if (ue!= null){
            return true;
        }else{
            return false;
        }
    }

    //follow
    public standardRes follow(String id, String following_id){
        RelationEntity re=new RelationEntity(id,following_id);
        if (!checkId(following_id)) return new standardRes(301,"该用户不存在");
        try{
            relationDAO.save(re);
            return new standardRes(0,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(302,e.toString());
        }
    }


    //取消follow
    public standardRes cancelFollow(String follower_id,String followee_id){
        RelationEntity re = relationDAO.findByFollowerIdAndFolloweeId(follower_id,followee_id);
        if (re==null) return new standardRes(304,"follow关系不存在");
        try{
            relationDAO.delete(re);
            return new standardRes(0,"取消follow成功");
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    //获取用户follow的人列表
    public following_ListRes getFollowing(String id){
        List<RelationEntity> re=relationDAO.findByFollowerId(id);
        List<userInfo> infoList=new ArrayList<>();
        for (int i=0;i<re.size();i++){
            UserEntity ue=userDAO.findById(re.get(i).getFolloweeId());
            userInfo ui = new userInfo(null,re.get(i).getFolloweeId(),ue.getName(),ue.getAvatar(),ue.getEmail(),ue.getLocation(),ue.getGender(),ue.getSummary(),ue.getHomepage(),ue.getRegisterDate().toString(),ue.getFollowingNum(),ue.getFollowerNum());
            infoList.add(ui);
        }
        return new following_ListRes(0,id,infoList);
    }

    //获取follow用户的人的列表
    public follower_ListRes getFollower(String id){
        List<RelationEntity> re=relationDAO.findByFolloweeId(id);
        List<userInfo> infoList=new ArrayList<>();
        for (int i=0;i<re.size();i++){
            UserEntity ue=userDAO.findById(re.get(i).getFollowerId());
            userInfo ui=new userInfo(re.get(i).getFollowerId(),null,ue.getName(),ue.getAvatar(),ue.getEmail(),ue.getLocation(),ue.getGender(),ue.getSummary(),ue.getHomepage(),ue.getRegisterDate().toString(),ue.getFollowingNum(),ue.getFollowerNum());
            infoList.add(ui);
        }
        return new follower_ListRes(0,id,infoList);
    }

}
