package com.cody.demo.service.impl;

import com.cody.demo.dao.entity.Role;
import com.cody.demo.dao.entity.UserDetail;
import com.cody.demo.mapper.DemoMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @File Name: com.cody.demo.service.impl
 * @Author: WQL //作者及
 * @Date: 2019/4/3 9:54//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component(value = "CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService
{

    private final DemoMapper demoMapper;

    public CustomUserDetailsServiceImpl(DemoMapper demoMapper)
    {
        this.demoMapper = demoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
    {
        UserDetail userDetail = demoMapper.findByUsername(name);
        if (userDetail == null)
        {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        Role role = demoMapper.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }
}