package com.cody.demo.mapper;

import com.cody.demo.dao.entity.Role;
import com.cody.demo.dao.entity.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @File Name: com.cody.demo.mapper
 * @Author: WQL //作者及
 * @Date: 2019/4/2 16:44//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Repository
public interface DemoMapper
{
    /**
     * 根据用户名查找用户
     *
     * @param name
     * @return
     */
    UserDetail findByUsername(@Param("name") String name);


    /**
     * 创建新用户
     *
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据角色Id查找角色
     *
     * @param roleId
     * @return
     */
    Role findRoleById(@Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     *
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") long userId);
}
