package com.cody.demo.dao;

/**
 * @File Name: com.cody.demo.dao
 * @Author: WQL //作者及
 * @Date: 2019/4/2 16:06//完成日期
 * @Description: // 状态码
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public enum ResultCode
{
    /**
     * 请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),

    BAD_REQUEST(400, "参数或语法不对"),
    UNAUTHORIZED(401, "认证失败"),
    LOGIN_ERROR(401, "用户名或密码无效"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    OPERATE_ERROR(405, "操作失败，请求操作的资源不存在"),
    TIME_OUT(408, "请求超时"),

    SERVER_ERROR(500, "服务器内部错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
}
