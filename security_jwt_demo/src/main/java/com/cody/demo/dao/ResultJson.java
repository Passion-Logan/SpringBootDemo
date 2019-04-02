package com.cody.demo.dao;

import sun.plugin2.message.Serializer;

import java.io.Serializable;

/**
 * @File Name: com.cody.demo.dao
 * @Author: WQL //作者及
 * @Date: 2019/4/2 16:17//完成日期
 * @Description: // 返回类型
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class ResultJson<T> implements Serializable
{
    private static final long serialVersionUID = 783015033603078674L;
    private int code;
    private String msg;
    private T data;

    public static ResultJson ok()
    {
        return ok("");
    }

    public static ResultJson ok(Object o)
    {
        return new ResultJson(ResultCode.SUCCESS, o);
    }

    public static ResultJson failure(ResultCode code)
    {
        return failure(code, "");
    }

    public static ResultJson failure(ResultCode code, Object o)
    {
        return new ResultJson(code, o);
    }

    public ResultJson(ResultCode resultCode, T data)
    {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode)
    {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    @Override
    public String toString()
    {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg + '\"' +
                ", \"data\":\"" + data + '\"'+
                '}';
    }
}