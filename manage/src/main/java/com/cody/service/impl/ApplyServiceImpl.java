package com.cody.service.impl;

import com.cody.entity.ApplyEntity;
import com.cody.jpa.ApplyJPA;
import com.cody.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyJPA applyJPA;

    @Override
    public void addApply(ApplyEntity applyEntity) {
        applyJPA.save(applyEntity);
    }

    /**
     * 获取所有报名信息
     * @return
     */
    @Override
    public List<ApplyEntity> getApply() {
        return applyJPA.findAll();
    }

    /**
     * 获取指定活动的所有报名信息
     * @param username 用户学号
     * @return
     */
    @Override
    public List<ApplyEntity> getApplyByUsername(String username) {
        return applyJPA.findByUsername(username);
    }
}
