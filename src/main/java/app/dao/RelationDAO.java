package app.dao;

import app.entity.RelationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
public interface RelationDAO extends CrudRepository<RelationEntity,String> {
}