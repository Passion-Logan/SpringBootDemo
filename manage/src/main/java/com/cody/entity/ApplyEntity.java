package com.cody.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Cody_
 * @date 18/11/7
 */
@Data
@Entity
public class ApplyEntity {

    /**
     * 报名信息主键
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 报名活动主键
     */
    @Column(name = "activity_id", nullable = false)
    private Long activity_id;


    /**
     * 报名用户的学号
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 报名用户的姓名
     */
    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
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
}
