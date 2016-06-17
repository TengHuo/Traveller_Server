package app.dao;

import app.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
@Component
public interface ImageDAO extends CrudRepository<ImageEntity,String> {
    public List<ImageEntity> findByPostId(String id);
}
