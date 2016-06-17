package app.jsonClass;

import java.util.List;

/**
 * Created by tokki on 16/6/17.
 */
public class following_ListRes {
    private int errCode;
    private String user_id;
    private List<userInfo> following_List;

    public following_ListRes(int errCode, String user_id, List<userInfo> following_List) {
            this.following_List=following_List;
            this.errCode = errCode;
            this.user_id = user_id;
    }

    public following_ListRes(int errCode){
        this.errCode = errCode;
//        this.user_id = user_id;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List getFollowing_List(){return following_List;}

    public void setFollowing_List(List<userInfo> following_List){ this.following_List=following_List;}
}
