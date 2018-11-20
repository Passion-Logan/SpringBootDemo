package com.cody.entity;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 用户id， 自动增长
     */
    private Long id;

    @Column(name = "username")
    /**
     * 用户学号
     */
    private String username;

    @Column(name = "name")
    /**
     * 用户姓名
     */
    private String name;

    @Column(name = "department")
    /**
     * 用户系部
     */
    private String department;

    @Column(name = "clazz")
    /**
     * 用户班级
     */
    private String clazz;

    @Column(name = "qq")
    /**
     * 用户QQ
     */
    private String qq;

    @Column(name = "tel")
    /**
     * 用户联系电话
     */
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "password")
    /**
     * 用户密码
     */
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
