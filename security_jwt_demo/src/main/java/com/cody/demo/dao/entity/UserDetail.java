package com.cody.demo.dao.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @File Name: com.cody.demo.dao.entity
 * @Author: WQL //作者及
 * @Date: 2019/4/2 15:41//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class UserDetail implements UserDetails
{
    private long id;
    private String username;
    private String password;
    private Role role;
    private Date lastPasswordResetDate;

    public UserDetail(long id, String username, String password, Role role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDetail(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDetail(long id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * 返回分配给用户的用户列表
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    public long getId()
    {
        return id;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    /**
     * 账户是否未过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 账户是否未锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }


    /**
     * 密码是否未过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 账户是否激活
     *
     * @return
     */
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Date getLastPasswordResetDate()
    {
        return lastPasswordResetDate;
    }

    public Role getRole()
    {

        return role;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate)
    {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}