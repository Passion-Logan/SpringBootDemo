package com.cody.controller;

import com.alibaba.fastjson.JSON;
import com.cody.entity.ActivityEntity;
import com.cody.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cody_
 * @date 18/10/22
 */
@Controller
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     *获取活动
     **/
    @RequestMapping(value = "/fetchActivities")
    @ResponseBody
    public Object getActivity(@Param("page")Integer page) {
        /*return activityService.getActivity();*/
        System.out.println("当前页数:" + page);
        return activityService.selectAll(page);
    }

    /**
     * 管理员通过编辑活动表单提交修改后的活动
     * @param activityEdit 修改活动实体
     * @return
     */
    @RequestMapping(value = "/editActivity")
    @ResponseBody
    public String editActivity(@ModelAttribute(value = "activityEdit")ActivityEntity activityEdit) {
        //测试用
        System.out.println("活动id：" + activityEdit.getId());
        System.out.println("活动名称：" + activityEdit.getName());
        System.out.println("活动地点：" + activityEdit.getSite());
        System.out.println("开始时间：" + activityEdit.getStart());
        System.out.println("活动状态：" + activityEdit.getFlag());
        System.out.println("活动简介：" + activityEdit.getIntro());

        ActivityEntity editActivity = activityService.getById(activityEdit.getId());

        editActivity.setFlag(activityEdit.getFlag());
        editActivity.setName(activityEdit.getName());
        editActivity.setSite(activityEdit.getSite());
        editActivity.setIntro(activityEdit.getIntro());
        editActivity.setStart(activityEdit.getStart());

        activityService.updateActivity(editActivity);

        return "修改成功";
    }

    /**
     * 活动管理界面返回数据
     * @return
     */
    @RequestMapping(value = "/allActivity")
    @ResponseBody
    public Object allActivity(@Param("delId") String delId,
                              @Param("startId")String startId,
                              @Param("endId")String endId,
                              @Param("page")Integer page,
                              @Param("limit")Integer limit) {
        List<ActivityEntity> activity = activityService.getActivity(page, limit);

        List<ActivityEntity> all = activityService.all();

        //调试用
        /*System.out.println("删除活动的id：" + delId);
        System.out.println("开始活动的id：" + startId);
        System.out.println("结束活动的id：" + endId);*/

        //根据活动Id开始活动
        if(startId != null && endId == null) {
            ActivityEntity start = activityService.getById(Long.parseLong(startId));
            start.setFlag(1);
            activityService.updateActivity(start);

            Map startActivity = new HashMap();
            startActivity.put("msg", "加载成功!");
            startActivity.put("count", all.size());
            startActivity.put("code", 0);
            startActivity.put("data", activity);

            return JSON.toJSONString(startActivity);
        }

        //根据活动Id结束活动
        if(endId != null) {
            ActivityEntity end = activityService.getById(Long.parseLong(endId));
            end.setFlag(2);
            activityService.updateActivity(end);

            Map endActivity = new HashMap();
            endActivity.put("msg", "加载成功!");
            endActivity.put("count", all.size());
            endActivity.put("code", 0);
            endActivity.put("data", activity);

            return JSON.toJSONString(endActivity);
        }

        //根据活动Id删除活动
        if(delId != null) {
            activityService.delById(Long.parseLong(delId));

            List<ActivityEntity> delActivity = activityService.getActivity(page, limit);

            Map del = new HashMap();
            del.put("msg", "加载成功!");
            del.put("count", all.size());
            del.put("code", 0);
            del.put("data", delActivity);

            return JSON.toJSONString(del);
        }

        Map newActivity = new HashMap();
        newActivity.put("msg", "加载成功!");
        newActivity.put("count", all.size());
        newActivity.put("code", 0);
        newActivity.put("data", activity);

        return JSON.toJSONString(newActivity);
    }

    /**
     * 发布活动
     * @param activityAdd 发布活动实体
     * @return
     */
    @RequestMapping(value = "/addActivity")
    @ResponseBody
    public Object addActivity(@ModelAttribute(value = "activityAdd")ActivityEntity activityAdd) {
        activityAdd.setFlag(0);
        activityAdd.setApply(0);
        activityService.updateActivity(activityAdd);

        return "发布成功！";
    }

}
