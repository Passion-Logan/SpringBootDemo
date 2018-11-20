package com.cody.service;

import com.cody.entity.ApplyEntity;

import java.util.List;

/**
 * @author Cody_
 * @date 18/11/7
 */
public interface ApplyService {

    /**
     * 添加报名信息
     * @param applyEntity
     */
    void addApply(ApplyEntity applyEntity);

    /**
     * 返回所有的报名信息
     * @return
     */
    List<ApplyEntity> getApply();

    /**
     * 根据学号返回用户报名的活动
     * @param username
     * @return
     */
    List<ApplyEntity> getApplyByUsername(String username);
}
