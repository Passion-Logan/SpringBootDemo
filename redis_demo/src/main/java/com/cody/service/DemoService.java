package com.cody.service;

import com.cody.dao.ResultJson;
import com.cody.dao.entity.DemoEntity;

import java.util.List;

/**
 * @File Name: com.cody.service
 * @Author: WQL //作者及
 * @Date: 2019/4/15 11:29//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface DemoService
{

    /**
     * 获取所有用户
     * @return
     */
    List<DemoEntity> getUsers();

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    DemoEntity getById(Integer id);

    /**
     * 添加用户
     * @param demoEntity
     * @return
     */
    int saveUser(DemoEntity demoEntity);


    /**
     * 更新用户
     * @param demoEntity
     * @return
     */
    int updateUser(DemoEntity demoEntity);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);

}