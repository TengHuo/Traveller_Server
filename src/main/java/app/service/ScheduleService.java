package app.service;

import app.dao.ScheduleDAO;
import app.entity.ScheduleEntity;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by zhujay on 16/6/19.
 */
@Service
public class ScheduleService {

    @Autowired
    ScheduleDAO scheduleDAO;

    public standardRes createSchedule(String creatorId, String destination, String scheduleDate,String image_url){
        ScheduleEntity se = new ScheduleEntity(destination,scheduleDate,image_url,creatorId);

        try{
            scheduleDAO.save(se);
            return new standardRes();
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }

    public standardRes deleteSchedule(String scheduleId){
        ScheduleEntity se = scheduleDAO.findById(scheduleId);
        if(se == null) return new standardRes(503,"schedule不存在");

        try {
            scheduleDAO.delete(se);
            return new standardRes();
        }catch (Exception e){
            e.printStackTrace();
            return new standardRes(999,e.toString());
        }
    }


}
