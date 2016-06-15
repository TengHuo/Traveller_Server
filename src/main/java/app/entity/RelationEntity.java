package app.entity;

import javax.persistence.*;

/**
 * Created by zhujay on 16/6/15.
 */
@Entity
@Table(name = "relation", schema = "traveller", catalog = "")
@IdClass(RelationEntityPK.class)
public class RelationEntity {
    private String followerId;
    private String followeeId;

    public RelationEntity(String followerId, String followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public RelationEntity() {
    }

    @Id
    @Column(name = "follower_id")
    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    @Id
    @Column(name = "followee_id")
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

        RelationEntity that = (RelationEntity) o;

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
