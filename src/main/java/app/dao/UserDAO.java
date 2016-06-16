package app.dao;

import app.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface UserDAO extends CrudRepository<UserEntity,String> {
    public UserEntity findByEmail(String email);
    public UserEntity findByName(String name);
}
