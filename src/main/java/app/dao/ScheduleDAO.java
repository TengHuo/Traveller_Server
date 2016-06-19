package app.dao;

import app.entity.ScheduleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujay on 16/6/19.
 */
@Transactional
@Component
public interface ScheduleDAO extends CrudRepository<ScheduleEntity,String> {
    public ScheduleEntity findById(String id);
    public List<ScheduleEntity> findByCreatorId(String creatorId);
}
