package app.dao;

import app.entity.RelationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/15.
 */
@Transactional
@Component
public interface RelationDAO extends CrudRepository<RelationEntity,String> {
    public RelationEntity findByFollowerIdAndFolloweeId(String followerId,String followeeId);
    public List<RelationEntity> findByFollowerId(String followerId);
    public List<RelationEntity> findByFolloweeId(String followeeId);
}
