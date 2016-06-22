package app.dao;

import app.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
@Component
public interface UserDAO extends CrudRepository<UserEntity,String> {
    public UserEntity findByEmail(String email);
    public UserEntity findByName(String name);
    public UserEntity findById(String id);
    @Transactional
    @Modifying
    @Query("update UserEntity ue set ue.followerNum = ue.followerNum+1 where ue.id = :followeeid")
    public int updateFollowerNum(@Param("followeeid")String followeeid);

    @Transactional
    @Modifying
    @Query("update UserEntity ue set ue.followerNum = ue.followerNum-1 where ue.id = :followeeid")
    public int deleteFollowerNum(@Param("followeeid")String followeeid);

    @Transactional
    @Modifying
    @Query("update UserEntity ue set ue.followingNum = ue.followingNum+1 where ue.id = :followerid")
    public int updateFolloweeNum(@Param("followerid")String followerid);

    @Transactional
    @Modifying
    @Query("update UserEntity ue set ue.followingNum = ue.followingNum-1 where ue.id = :followerid")
    public int deleteFolloweeNum(@Param("followerid")String followerid);
}
