package com.cody.oauth.jpa;

import com.cody.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @File Name: com.cody.oauth.jpa
 * @Author: WQL //作者及
 * @Date: 2019/3/28 10:49//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface UserJPA extends JpaRepository<User, String>
{

    @Query("select u from User u where lower(u.username) = lower(:username)")
    User findByUsernameCaseInsensitive(@Param("username") String username);
}
