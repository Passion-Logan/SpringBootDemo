package com.cody.controller;

import com.cody.dto.User;
import com.cody.entity.UserEntity;
import com.cody.entity.UserLogEntity;
import com.cody.mapper.UserEntityMapper;
import com.cody.mapper.UserLogEntityMapper;
import com.cody.response.BaseResponse;
import com.cody.response.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/6/10 16:33//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class UserController
{

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private static final String Prefix = "user";

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserLogEntityMapper userLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @RequestMapping(value = Prefix+"/login",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            UserEntity user = userMapper.selectByUserNamePassword(userName,password);
            if (user!=null){

                //TODO：异步写用户日志
                try {
                    UserLogEntity userLog = new UserLogEntity(userName,"Login","login",objectMapper.writeValueAsString(user));
                    userLog.setCreateTime(new Date());
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
                    rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

                    Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                    //发送消息写法二
                    message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
                    rabbitTemplate.convertAndSend(message);


                    /*UserLog log=new UserLog(userName,"Login","login",objectMapper.writeValueAsString(user));
                    userLogMapper.insertSelective(log);*/ //同步

                    /*MessageProperties properties=new MessageProperties();
                    properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    properties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
                    Message message=new Message(objectMapper.writeValueAsBytes(userLog),properties);*/ //发送消息写法一
                }catch (Exception e){
                    e.printStackTrace();
                }

                //TODO：塞权限数据-资源数据-视野数据

            }else{
                response=new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}