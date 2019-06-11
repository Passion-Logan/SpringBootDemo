package com.cody.rabbitmq;

import com.cody.entity.UserLogEntity;
import com.cody.mapper.UserLogEntityMapper;
import com.cody.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @File Name: com.cody.rabbitmq
 * @Author: WQL //作者及
 * @Date: 2019/6/10 17:22//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class CommonMqListener
{

    private static final Logger log= LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserLogEntityMapper userLogMapper;

    @Autowired
    private MailService mailService;

    /**
     * 监听消费用户日志
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message){
        try {
            UserLogEntity userLog=objectMapper.readValue(message, UserLogEntity.class);
            log.info("监听消费用户日志 监听到消息： {} ",userLog);

            userLogMapper.insertSelective(userLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 监听消费邮件发送
     * @param message
     */
    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMailQueue(@Payload byte[] message){
        try {
            log.info("监听消费邮件发送 监听到消息： {} ",new String(message,"UTF-8"));

            mailService.sendEmail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 监听消费死信队列中的消息
     * @param message
     */
    @RabbitListener(queues = "${simple.dead.real.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeDeadQueue(@Payload byte[] message){
        try {
            log.info("监听消费死信队列中的消息： {} ",new String(message,"UTF-8"));

            mailService.sendEmail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}