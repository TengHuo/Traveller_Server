package app.dao;

import app.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface CommentDAO extends CrudRepository<CommentEntity,String> {

}
