package com.cody.oauth.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @File Name: com.cody.oauth.entity
 * @Author: WQL //作者及
 * @Date: 2019/3/28 10:17//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Entity
@Table(name = "user")
public class User
{
    @Id
    @Column(updatable = false, nullable = false)
    @Size(min = 0, max = 50)
    private String username;

    @Size(min = 0, max = 500)
    private String password;

    @Size(min = 0, max = 50)
    private String email;

    private boolean activated;

    @Size(min = 0, max = 100)
    @Column(name = "activationkey")
    private String activationKey;

    @Size(min = 0, max = 100)
    @Column(name = "resetpasswordkey")
    private String resetPasswordKey;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", activated='" + activated + '\'' +
                ", activationKey='" + activationKey + '\'' +
                ", resetPasswordKey='" + resetPasswordKey + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}