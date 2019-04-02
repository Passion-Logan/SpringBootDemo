package com.cody.oauth;

/**
 * @File Name: com.cody.oauth
 * @Author: WQL //作者及
 * @Date: 2019/3/28 11:12//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class NewUserNotFoundException extends RuntimeException
{

    public NewUserNotFoundException(String msg, Throwable t)
    {
        super(msg, t);
    }

    public NewUserNotFoundException(String msg)
    {
        super(msg);
    }

}