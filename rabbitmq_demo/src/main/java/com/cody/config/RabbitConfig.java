package com.cody.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

/**
 * @File Name: com.cody.config
 * @Author: WQL //作者及
 * @Date: 2019/6/4 16:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Configuration
public class RabbitConfig
{

    public Queue helloQueue() {
        return new Queue("hello");
    }

}