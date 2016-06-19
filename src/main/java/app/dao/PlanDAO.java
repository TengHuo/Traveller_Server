package app.dao;

import app.entity.PlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tokki on 16/6/19.
 */
@Transactional
@Component
public interface PlanDAO extends CrudRepository<PlanEntity,String>{
    public List<PlanEntity> findByScheduleId(String ScheduleId);
    public PlanEntity findByPlanId(String PlanId);
}
