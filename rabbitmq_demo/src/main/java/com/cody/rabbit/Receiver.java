package com.cody.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @File Name: com.cody.rabbit
 * @Author: WQL //作者及
 * @Date: 2019/6/4 16:44//完成日期
 * @Description: // 消费者
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver
{

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}