package com.cody.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Cody_
 * @date 18/9/25
 */
@Entity
public class UserEntity implements Serializable {

    @Id
    @Column(name = "t_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 用户id
     */
    private Long id;

    @NotEmpty(message = "学号不能为空")
    @Column(name = "t_username", nullable = false)
    /**
     * 用户学号
     */
    private String username;

    @NotEmpty(message = "姓名不能为空")
    @Column(name = "t_name", nullable = false)
    /**
     * 用户姓名
     */
    private String name;

    @NotEmpty(message = "系部不能为空")
    @Column(name = "t_department", nullable = false)
    /**
     * 用户系部
     */
    private String department;

    @NotEmpty(message = "班级不能为空")
    @Column(name = "t_clazz", nullable = false)
    /**
     * 用户班级
     */
    private String clazz;

    @NotEmpty(message = "密码不能为空")
    @Column(name = "t_password", nullable = false)
    /**
     * 用户密码
     */
    private String password;

    @NotEmpty(message = "QQ不能为空")
    @Column(name = "t_qq", nullable = false)
    /**
     * 用户QQ
     */
    private String qq;

    @NotEmpty(message = "邮箱不能为空")
    @Column(name = "t_email", nullable = false)
    /**
     * 用户邮箱
     */
    private String email;

    @NotEmpty(message = "手机号码不能为空")
    @Column(name = "t_tel", nullable = false)
    /**
     * 用户邮箱
     */
    private String tel;

    public String getRoles() {
        return roles;
    }

    /**
     * 权限
     */
    @Column(name = "t_roles")
    private String roles;

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
