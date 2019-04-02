package com.cody.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody.oauth.controller
 * @Author: WQL //作者及
 * @Date: 2019/3/28 10:59//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
@RequestMapping("secure")
public class SecureController
{
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello()
    {
        return "Secure!";
    }

}