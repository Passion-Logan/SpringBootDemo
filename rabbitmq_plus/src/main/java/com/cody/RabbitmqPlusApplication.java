package com.cody;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @File Name: com.cody
 * @Author: WQL //作者及
 * @Date: 2019/6/11 10:35//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cody.mapper")
public class RabbitmqPlusApplication
{

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqPlusApplication.class, args);
    }

}