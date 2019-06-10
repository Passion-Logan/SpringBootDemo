package com.cody.controller;

import com.cody.listener.event.PushOrderRecordEvent;
import com.cody.response.BaseResponse;
import com.cody.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/6/10 15:19//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class OrderRecordController
{

    private static final Logger log= LoggerFactory.getLogger(OrderRecordController.class);

    private static final String Prefix="order";

    //TODO：类似于RabbitTemplate

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 下单
     * @return
     */
    @RequestMapping(value = Prefix+"/push",method = RequestMethod.GET)
    public BaseResponse pushOrder(){
        BaseResponse response=new BaseResponse(StatusCode.Success);

        try {
            PushOrderRecordEvent event=new PushOrderRecordEvent(this,"orderNo_20180821001","物流12");
            publisher.publishEvent(event);
        }catch (Exception e){
            log.error("下单发生异常：",e.fillInStackTrace());
        }

        return response;
    }



}