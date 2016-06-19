package app.controller;

import app.jsonClass.DayDetailRes;
import app.jsonClass.standardRes;
import app.service.DaydetailService;
import app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jixiang on 2016/6/19.
 */

@RestController
public class DaydetailController {
    @Autowired
    DaydetailService daydetailService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/daily/tour", method = RequestMethod.GET)

    public DayDetailRes getDayDetails(@RequestParam String token,
                                      @RequestParam String id) {
        String _id = tokenService.token2id(token);
        if(_id == null) return new DayDetailRes(105,"token异常");
        if(id == null) return new DayDetailRes(701, "plan id不能为空");

        try {
            return daydetailService.getDetails(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new DayDetailRes(999, e.toString());
        }

    }

    @RequestMapping(value = "/daily/add", method = RequestMethod.POST)

    public standardRes addDayDetail(@RequestParam String token,
                                    @RequestParam String user_id,
                                    @RequestParam String plan_id,
                                    @RequestParam (value = "post_id",defaultValue = "") String post_id,
                                    @RequestParam (value = "start_time",defaultValue = "") String start_time,
                                    @RequestParam (value = "end_time",defaultValue = "") String end_time,
                                    @RequestParam (value = "destination",defaultValue = "") String destination,
                                    @RequestParam (value = "latitude",defaultValue = "0.0") Double latitude,
                                    @RequestParam (value = "longitude",defaultValue = "0.0") Double longitude,
                                    @RequestParam (value = "type",defaultValue = "0") int type
    ) {
        String _id = tokenService.token2id(token);
        if (_id == null || !_id.equals(user_id)) return new standardRes(105,"token异常");
        if (plan_id == null) return new standardRes(701, "plan id不能为空");

        try {
            return daydetailService.addDayDetail(plan_id, post_id, start_time, end_time, destination, latitude, longitude, type);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }
    }

    @RequestMapping(value = "/daily/delete/{token}/{user_id}/{detail_id}", method = RequestMethod.DELETE)

    public standardRes deleteDayDetail(@PathVariable("token") String token,
                                       @PathVariable("user_id") String user_id,
                                       @PathVariable("detail_id") String detail_id) {
        String _id = tokenService.token2id(token);
        if (_id == null || !_id.equals(user_id)) return new standardRes(105,"token异常");
        if (detail_id == null) return new standardRes(706, "dayDetail id不能为空");

        try {
            return daydetailService.deleteDayDetail(detail_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new standardRes(999, e.toString());
        }

    }
}
