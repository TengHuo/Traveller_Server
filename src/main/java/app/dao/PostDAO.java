package app.dao;

import app.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface PostDAO extends CrudRepository<PostEntity,String> {
    public PostEntity findById(String id);
}
