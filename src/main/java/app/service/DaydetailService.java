package app.service;

import app.dao.DaydetailDAO;
import app.dao.PlanDAO;
import app.entity.DayDetailEntity;
import app.jsonClass.DayDetail;
import app.jsonClass.DayDetailRes;
import app.jsonClass.standardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiang on 2016/6/19.
 */

@Service
public class DaydetailService {
    @Autowired
    DaydetailDAO dayDetailDAO;

    @Autowired
    PlanDAO planDAO;

    public DayDetailRes getDetails(String plan_id) {
        if (dayDetailDAO.findByPlanId(plan_id).equals("[]")) return new DayDetailRes(702, "plan id不存在");

        try {
            List<DayDetailEntity> dayDetailEntities = dayDetailDAO.findAllByPlanId(plan_id);
            List<DayDetail> dayDetails = new ArrayList<>();

            dayDetailEntities.forEach(dayDetailEntity -> {
                DayDetail dayDetail = new DayDetail();

                dayDetail.setId(dayDetailEntity.getId());
                dayDetail.setPlan_id(dayDetailEntity.getPlanId());
                dayDetail.setPost_id(dayDetailEntity.getPostId());
                dayDetail.setStart_time(dayDetailEntity.getStartTime());
                dayDetail.setEnd_time(dayDetailEntity.getEndTime());
                dayDetail.setDestination(dayDetailEntity.getDestination());
                dayDetail.setLatitude(dayDetailEntity.getLatitude());
                dayDetail.setLongitude(dayDetailEntity.getLongitude());
                dayDetail.setType(dayDetailEntity.getType());

                dayDetails.add(dayDetail);
            });
            System.out.println(dayDetails);
            return new DayDetailRes(0, dayDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new DayDetailRes(703, "获取失败");
        }
    }

    public standardRes addDayDetail(String plan_id,
                                    String post_id,
                                    String start_time,
                                    String end_time,
                                    String destination,
                                    Double latitude,
                                    Double longitude,
                                    int type) {
        if (planDAO.findByPlanId(plan_id) == null) return new standardRes(702, "plan id不存在");
        try {
            DayDetailEntity de = new DayDetailEntity(plan_id, post_id, start_time, end_time, destination, latitude, longitude, type);
            dayDetailDAO.save(de);
            return new standardRes(0, de.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(703, "获取失败");
        }
    }

    public standardRes deleteDayDetail(String detail_id) {
        if (dayDetailDAO.findById(detail_id) == null) return new standardRes(707, "dayDetail id不存在");

        try {
            dayDetailDAO.delete(dayDetailDAO.findById(detail_id));
            return new standardRes(0, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(708, "删除失败");
        }
    }

}
