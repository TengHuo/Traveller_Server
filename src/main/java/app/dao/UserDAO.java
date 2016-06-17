package app.dao;

import app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
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
}
