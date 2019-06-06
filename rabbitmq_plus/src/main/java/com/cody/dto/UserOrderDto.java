package com.cody.dto;

import java.io.Serializable;

/**
 * @File Name: com.cody.dto
 * @Author: WQL //作者及
 * @Date: 2019/6/6 17:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class UserOrderDto implements Serializable
{
    private String orderNo;

    private Integer userId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserOrderDto{" +
                "orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                '}';
    }
}