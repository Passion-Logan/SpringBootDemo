package com.cody.controller;

import com.cody.dto.User;
import com.cody.response.BaseResponse;
import com.cody.response.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File Name: com.cody
 * @Author: WQL //作者及
 * @Date: 2019/6/10 14:40//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class AcknowledgeController
{
    private static final Logger log = LoggerFactory.getLogger(AcknowledgeController.class);

    private static final String Prefix = "ack";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @RequestMapping(value = Prefix+"/user/info",method = RequestMethod.GET)
    public BaseResponse ackUser(){
        User user=new User(1,"debug","steadyjack");
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("simple.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("simple.mq.routing.key.name"));

            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(user)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            rabbitTemplate.convertAndSend(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BaseResponse(StatusCode.Success);
    }

}