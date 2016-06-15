package app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zhujay on 16/6/15.
 */
public class RelationEntityPK implements Serializable {
    private String followerId;
    private String followeeId;

    @Column(name = "follower_id")
    @Id
    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    @Column(name = "followee_id")
    @Id
    public String getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(String followeeId) {
        this.followeeId = followeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationEntityPK that = (RelationEntityPK) o;

        if (followerId != null ? !followerId.equals(that.followerId) : that.followerId != null) return false;
        if (followeeId != null ? !followeeId.equals(that.followeeId) : that.followeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = followerId != null ? followerId.hashCode() : 0;
        result = 31 * result + (followeeId != null ? followeeId.hashCode() : 0);
        return result;
    }
}
