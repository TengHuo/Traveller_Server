package app.jsonClass;

import java.util.List;

/**
 * Created by tokki on 16/6/17.
 */
public class follower_ListRes {
    private int errCode;
    private String user_id;
    private List<userInfo> follower_List;

    public follower_ListRes(int errCode, String user_id, List<userInfo> follower_List) {
            this.follower_List=follower_List;
            this.errCode = errCode;
            this.user_id = user_id;
    }

    public follower_ListRes(int errCode){
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

    public List getFollower_List(){return follower_List;}

    public void setFollower_List(List<userInfo> follower_List){ this.follower_List=follower_List;}
}
