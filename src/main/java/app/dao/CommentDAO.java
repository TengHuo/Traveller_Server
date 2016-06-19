package app.dao;

import app.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
@Component
public interface CommentDAO extends CrudRepository<CommentEntity,String> {
    public List<CommentEntity> findByCreaterId(String id);
    public List<CommentEntity> findByPostId(String id);
    public CommentEntity findByCommentId(String id);
}
