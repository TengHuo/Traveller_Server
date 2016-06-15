package app.dao;

import app.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface ImageDAO extends CrudRepository<ImageEntity,String> {
}
