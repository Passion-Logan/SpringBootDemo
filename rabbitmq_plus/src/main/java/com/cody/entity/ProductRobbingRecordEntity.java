package com.cody.entity;

import java.util.Date;

public class ProductRobbingRecordEntity {
    private Integer id;

    private String mobile;

    private Integer productId;

    private Date robbingTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getRobbingTime() {
        return robbingTime;
    }

    public void setRobbingTime(Date robbingTime) {
        this.robbingTime = robbingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}