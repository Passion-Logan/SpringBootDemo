package com.cody.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @File Name: com.cody.rabbit
 * @Author: WQL //作者及
 * @Date: 2019/6/4 16:35//完成日期
 * @Description: // 生产者
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class Sender
{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello" + new Date();
        System.out.println("Sender" + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}