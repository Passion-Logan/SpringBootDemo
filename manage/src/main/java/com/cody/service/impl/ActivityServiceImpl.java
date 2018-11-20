package com.cody.service.impl;

import com.alibaba.fastjson.JSON;
import com.cody.entity.ActivityEntity;
import com.cody.entity.ApplyEntity;
import com.cody.entity.UserEntity;
import com.cody.jpa.ActivityJPA;
import com.cody.jpa.ApplyJPA;
import com.cody.service.ActivityService;
import com.cody.utils.ListSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cody_
 * @date 18/10/22
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityJPA activityJPA;

    @Autowired
    ApplyJPA applyJPA;

    @Autowired
    ListSort listSort;

    /**
     * 获取所有活动信息
     * @param page 页数
     * @param limit 每页数量
     * @return 以List形式返回所有活动信息
     */
    @Override
    public List<ActivityEntity> getActivity(Integer page, Integer limit) {

        Pageable pageable = PageRequest.of(page-1, limit);
        Page<ActivityEntity> activityEntityPage = activityJPA.findAll(pageable);

        Iterator<ActivityEntity> activityEntityIterator =  activityEntityPage.iterator();

        //分页后的List
        List<ActivityEntity> activityAll = new ArrayList<>();

        while(activityEntityIterator.hasNext()) {
            activityAll.add(activityEntityIterator.next());
        }

        listSort.ActivitySortByTime(activityAll);

        return activityAll;
    }


    @Override
    public List<ActivityEntity> all() {
        List<ActivityEntity> all = activityJPA.findAll();
        return all;
    }

    /**
     * 根据页数返回指定数量的条数，默认每页4条
     * @param pageNum 页数
     * @return
     */
    @Override
    public Object selectAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1, 4, Sort.Direction.DESC, "id");
        Page<ActivityEntity> pageActivity = activityJPA.findAll(pageable);

        Iterator<ActivityEntity> pageIterator =  pageActivity.iterator();

        //分页后的List
        List<ActivityEntity> pageList = new ArrayList<>();

        while(pageIterator.hasNext()) {
            pageList.add(pageIterator.next());
        }

        //获取登录用户的学号
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //该用户的报名信息
        List<ApplyEntity> applyEntities = applyJPA.findByUsername(username);

        //将登录用户所有报名的id存入数组
        ArrayList<String> idArray = new ArrayList<String>();
        if(applyEntities.size() != 0) {
            for(int i = 0; i < applyEntities.size(); i++) {
                ApplyEntity idApply = applyEntities.get(i);

                idArray.add(Long.toString(idApply.getActivity_id()));
            }
        }

        //判断活动id在用户报名活动id中是否存在，存在设置报名状态为1，否则为0（1已报名，0未报名）
        if(idArray.size() != 0) {
            for(int i = 0; i < pageList.size(); i++) {
                ActivityEntity forActivity = pageList.get(i);
                String targetId = Long.toString(forActivity.getId());

                if (idArray.contains(targetId)) {
                    forActivity.setApply(1);
                }else {
                    continue;
                }
            }

            return JSON.toJSONString(pageList);
        }else {
            for(int i = 0; i < pageList.size(); i++) {
                ActivityEntity forActivity = pageList.get(i);
                forActivity.setApply(0);
            }

            return JSON.toJSONString(pageList);
        }
    }

    /**
     * 根据活动主键删除活动
     * @param id id主键
     */
    @Override
    public void delById(Long id) {
        activityJPA.deleteById(id);
    }

    /**
     * 根据活动主键获取活动实体
     * @param id id 主键
     * @return 活动实体
     */
    @Override
    public ActivityEntity getById(Long id) {
        return activityJPA.getActivityEntityById(id);
    }

    /**
     * 更新活动信息
     * @param activityEntity 活动实体
     */
    @Override
    public void updateActivity(ActivityEntity activityEntity) {
        activityJPA.save(activityEntity);
    }
}
