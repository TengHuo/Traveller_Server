package app.dao;

import app.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
@Component
public interface PostDAO extends CrudRepository<PostEntity,String> {
    public PostEntity findById(String id);

    /**
     * @Query 自定义查询
     * @param id
     * @return
     */
    @Query("SELECT pe FROM PostEntity pe WHERE pe.id = :id")
    public List<PostEntity> findAllById(@Param("id") String id);
}
