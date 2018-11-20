package com.cody.service;

import com.cody.entity.ActivityEntity;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * @author Cody_
 * @date 18/10/22
 */
public interface ActivityService {

    List<ActivityEntity> all();

    /**
     * 活动管理界面获取所有活动信息
     * @param page 页数
     * @param limit 每页数量
     * @return 以List形式返回所有活动信息
     */
    List<ActivityEntity> getActivity(Integer page, Integer limit);

    /**
     * 活动展示界面获取指定页数的内容
     * @param pageNum 页数
     * @return
     */
    Object selectAll(int pageNum);

    /**
     * 根据id删除活动信息
     * @param id id主键
     */
    void delById(Long id);

    /**
     * 根据id获取活动实体
     * @param id id 主键
     * @return 活动实体
     */
    ActivityEntity getById(Long id);

    /**
     * 更新活动信息
     * @param activityEntity 活动实体
     */
    void updateActivity(final ActivityEntity activityEntity);
}
