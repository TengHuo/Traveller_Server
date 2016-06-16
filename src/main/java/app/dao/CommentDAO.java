package app.dao;

import app.entity.CommentEntity;
import app.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface CommentDAO extends CrudRepository<CommentEntity,String> {
    public List<CommentEntity> findByCommentId(String id);
}
