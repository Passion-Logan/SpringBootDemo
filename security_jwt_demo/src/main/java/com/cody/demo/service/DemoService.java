package com.cody.demo.service;

import com.cody.demo.dao.entity.ResponseUserToken;
import com.cody.demo.dao.entity.UserDetail;

/**
 * @File Name: com.cody.demo.service
 * @Author: WQL //作者及
 * @Date: 2019/4/3 9:49//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface DemoService
{
    /**
     * 注册用户
     *
     * @param userDetail
     * @return
     */
    UserDetail register(UserDetail userDetail);

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);


    /**
     * 登出
     *
     * @param token
     */
    void logout(String token);

    /**
     * 刷新token
     *
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);

}