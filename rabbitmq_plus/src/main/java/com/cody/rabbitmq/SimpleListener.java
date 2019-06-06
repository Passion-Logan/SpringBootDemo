package com.cody.rabbitmq;

import com.cody.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @File Name: com.cody.rabbitmq
 * @Author: WQL //作者及
 * @Date: 2019/6/6 16:04//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component("simpleListener")
public class SimpleListener implements ChannelAwareMessageListener
{

    private static final Logger log = LoggerFactory.getLogger(SimpleListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception
    {
        long tag = message.getMessageProperties().getDeliveryTag();

        try
        {
            byte[] msg = message.getBody();
            User user = objectMapper.readValue(msg, User.class);
            log.info("简单消息监听确认机制监听到消息： {} ", user);

            channel.basicAck(tag, true);
        } catch (Exception e)
        {
            log.error("简单消息监听确认机制发生异常：",e.fillInStackTrace());

            channel.basicReject(tag, false);
        }
    }
}