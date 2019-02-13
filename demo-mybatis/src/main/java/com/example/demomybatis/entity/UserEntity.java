package com.example.demomybatis.entity;

import java.io.Serializable;

/**
 * @File Name: com.example.demomybatis.entity
 * @Author: WQL //作者及
 * @Date: 2019/1/8 13:27//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;

    public UserEntity() {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}