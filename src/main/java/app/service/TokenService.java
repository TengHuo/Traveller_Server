package app.service;

import app.dao.TokenDAO;
import app.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhujay on 16/6/17.
 */
@Service
public class TokenService {

    @Autowired
    TokenDAO tokenDAO;

    @Autowired
    UserService userService;

    public String token2id(String token){
        try {
            TokenEntity te = tokenDAO.findByToken(token);
            return (te == null || te.getExp() < new java.util.Date().getTime() / 1000) ? null : ( (tokenDAO.updateToken(token,getExpTime()) != 0) ? te.getUserId() : null  );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String id2token(String id) {
        try {
            TokenEntity te = tokenDAO.findByUserId(id);
            return (te == null) ? null : te.getToken();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //确认是否有这个userid
    private boolean idCheck(String userId){
        return userService.checkUserById(userId);
    }

    //生成新的过期时间
    private long getExpTime(){
        return new java.util.Date().getTime() / 1000 + 3600 * 2;
    }

//    public boolean checkExistBtToken(String token){
//        TokenEntity te = tokenDAO.findByToken(token);
//        return te != null;
//    }

    //确认是否存在这个userid对应的token
//    public boolean checkExistByUser(String userId){
//        TokenEntity te = tokenDAO.findByUserId(userId);
//        return te != null;
//    }

    public boolean createToken(String userId){
        if(!idCheck(userId)) return false;
        if(id2token(userId) != null) return false;
        TokenEntity te = new TokenEntity(userId,getExpTime());
        try {
            tokenDAO.save(te);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateToken(String userId){
        if(!idCheck(userId)) return false;
        if(id2token(userId) == null) return false;
        try {
            TokenEntity te = tokenDAO.findByUserId(userId);
            tokenDAO.delete(te);
            te = new TokenEntity(userId,getExpTime());
            tokenDAO.save(te);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
