package com.cody.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @File Name: com.cody.listener.event
 * @Author: WQL //作者及
 * @Date: 2019/6/10 15:22//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class PushOrderRecordEvent extends ApplicationEvent
{

    private String orderNo;

    private String orderType;

    public PushOrderRecordEvent(Object source, String orderNo, String orderType) {
        super(source);
        this.orderNo = orderNo;
        this.orderType = orderType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "PushOrderRecordEvent{" +
                "orderNo='" + orderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}