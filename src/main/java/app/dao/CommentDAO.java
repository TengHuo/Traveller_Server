package app.dao;

import app.entity.CommentEntity;
import app.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface CommentDAO extends CrudRepository<CommentEntity,String> {
    public CommentEntity findByCommentId(String id);
    @Query("SELECT CASE WHEN COUNT(ue) > 0 THEN true ELSE false END FROM UserEntity ue WHERE ue.id = :id")
    boolean ifIdExistsInUser(@Param("id") String id);

    @Query("SELECT CASE WHEN COUNT(pe) > 0 THEN true ELSE false END FROM PostEntity pe WHERE pe.id = :id")
    boolean ifIdExistsInPost(@Param("id") String id);
}
