package com.cody.entity;

import java.util.Date;

public class UserLogEntity {
    private Integer id;

    private String userName;

    private String module;

    private String operation;

    private String data;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UserLogEntity(String userName, String module, String operation, String data) {
        this.userName = userName;
        this.module = module;
        this.operation = operation;
        this.data = data;
    }

    public UserLogEntity() {
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", module='" + module + '\'' +
                ", operation='" + operation + '\'' +
                ", data='" + data + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}