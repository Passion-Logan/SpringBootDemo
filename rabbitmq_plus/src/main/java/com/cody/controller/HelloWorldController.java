package com.cody.controller;

import com.cody.mapper.OrderRecordEntityMapper;
import com.cody.response.BaseResponse;
import com.cody.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/6/10 14:58//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class HelloWorldController
{
    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private static final String Prefix = "helloWorld";

    @Autowired
    private OrderRecordEntityMapper orderRecordMapper;

    /**
     * 测试SpringBoot整合是否有问题-hello world
     *
     * @return
     */
    @RequestMapping(value = Prefix+"/rabbitmq",method = RequestMethod.GET)
    public BaseResponse rabbitmq(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        String str = "rabbitmq的第二阶段-spring boot整合rabbitmq";
        response.setData(str);
        return response;
    }

    /**
     * 整合mybatis访问数据列表
     *
     * @return
     */
    @RequestMapping(value = Prefix+"/data/list",method = RequestMethod.GET)
    public BaseResponse dataList(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setData(orderRecordMapper.selectAll());
        return response;
    }

}