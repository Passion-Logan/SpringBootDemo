package com.cody.demo.quartz.controller;

import com.cody.demo.quartz.entity.GoodInfoEntity;
import com.cody.demo.quartz.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody.demo.quartz.controller
 * @Author: WQL //作者及
 * @Date: 2019/4/30 14:11//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
@RequestMapping(value = "/demo")
public class GoodController
{

    /**
     * 商品业务逻辑实现
     */
    @Autowired
    private GoodInfoService goodInfoService;
    /**
     * 添加商品
     * @return
     */
    @RequestMapping(value = "/save")
    public Long save(GoodInfoEntity good) throws Exception
    {
        return goodInfoService.saveGood(good);
    }

}