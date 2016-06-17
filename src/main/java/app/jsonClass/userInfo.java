package app.jsonClass;

/**
 * Created by zhujay on 16/6/17.
 */
public class userInfo {
    private String follower_id;
    private String followee_id;
    private String name;
    private String avatar;
    private String email;
    private String location;
    private int gender;
    private String summary;
    private String homepage;
    private String register_date;
    private int following_num;
    private int follower_num;

    public userInfo() {
    }

    public userInfo(String follower_id,String followee_id,String name, String avatar, String email, String location, int gender, String summary, String homepage, String register_date, int following_num, int follower_num) {
        this.followee_id = followee_id;
        this.follower_id = follower_id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.location = location;
        this.gender = gender;
        this.summary = summary;
        this.homepage = homepage;
        this.register_date = register_date;
        this.following_num = following_num;
        this.follower_num = follower_num;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }

    public String getFollowee_id() {
        return followee_id;
    }

    public void setFollowee_id(String followee_id) {
        this.followee_id = followee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public int getFollowing_num() {
        return following_num;
    }

    public void setFollowing_num(int following_num) {
        this.following_num = following_num;
    }

    public int getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(int follower_num) {
        this.follower_num = follower_num;
    }
}
