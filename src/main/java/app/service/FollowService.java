package app.service;

import app.dao.RelationDAO;
import app.dao.UserDAO;
import app.entity.RelationEntity;
import app.entity.UserEntity;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
