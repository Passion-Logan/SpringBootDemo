package com.cody.rabbitmq;

import com.cody.dto.LogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @File Name: com.cody.rabbitmq
 * @Author: WQL //作者及
 * @Date: 2019/6/10 17:21//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class LogSystemListener
{

    private static final Logger log= LoggerFactory.getLogger(LogSystemListener.class);

    @RabbitListener(queues = "${log.system.queue.name}",containerFactory = "multiListenerContainer")
    public void consumeLogInfo(@Payload LogDto logDto){
        try {
            log.info("系统日志监听器监听监听到消息: {} ",logDto);

        }catch (Exception e){
            log.error("系统日志监听器监听发生异常：{} ",logDto,e.fillInStackTrace());
        }
    }

}