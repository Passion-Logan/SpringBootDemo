package com.cody.oauth;

import com.cody.oauth.entity.Authority;
import com.cody.oauth.entity.User;
import com.cody.oauth.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @File Name: com.cody.oauth
 * @Author: WQL //作者及
 * @Date: 2019/3/28 11:02//完成日期
 * @Description: // 自定义UserDetailsService
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component("userDetailsService")
public class CodyUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserJPA userJPA;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login)
    {
        String lowercaseLogin = login.toLowerCase();

        User userFromDatabase = userJPA.findByUsernameCaseInsensitive(lowercaseLogin);

        System.out.println(userFromDatabase.getUsername() + "-----" + userFromDatabase.getPassword());

        if (userFromDatabase == null)
        {
            throw new NewUserNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }

        // 获取用户的所有权限并且SpringSecurity需要的集合
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities())
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        // 返回一个SpringSecurity需要的用户对象
        return new org.springframework.security.core.userdetails.User(
                userFromDatabase.getUsername(),
                userFromDatabase.getPassword(),
                grantedAuthorities
        );
    }
}