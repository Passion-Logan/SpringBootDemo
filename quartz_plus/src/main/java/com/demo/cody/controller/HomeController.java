package com.demo.cody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @File Name: com.demo.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/7/24 13:36//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Controller
@RequestMapping("home")
public class HomeController
{

    @GetMapping("root")
    public String root()
    {
        return "index";
    }

}