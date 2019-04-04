package com.cody.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody
 * @Author: WQL //作者及
 * @Date: 2019/4/1 16:22//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class HelloController
{

    @RequestMapping("hello")
    public String hello()
    {
        return "hello world!";
    }

}