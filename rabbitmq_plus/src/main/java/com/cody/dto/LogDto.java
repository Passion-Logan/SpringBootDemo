package com.cody.dto;

import java.io.Serializable;

/**
 * @File Name: com.cody.dto
 * @Author: WQL //作者及
 * @Date: 2019/6/10 11:44//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class LogDto implements Serializable
{
    private String methodName;

    private String operateData;

    public LogDto() {
    }

    public LogDto(String methodName, String operateData) {
        this.methodName = methodName;
        this.operateData = operateData;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOperateData() {
        return operateData;
    }

    public void setOperateData(String operateData) {
        this.operateData = operateData;
    }

    @Override
    public String toString() {
        return "LogDto{" +
                "methodName='" + methodName + '\'' +
                ", operateData='" + operateData + '\'' +
                '}';
    }
}