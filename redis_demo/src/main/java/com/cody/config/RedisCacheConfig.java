package com.cody.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @File Name: com.cody.config
 * @Author: WQL //作者及
 * @Date: 2019/4/12 17:48//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends JCacheConfigurerSupport
{

    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate)
    {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间 (单位：秒）
        rcm.setDefaultExpiration(60 * 24);
        return rcm;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        // 或者JdkSerializationRedisSerializer序列化方式;
        // Long类型不可以会出现异常信息;
        // RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // redisTemplate.setKeySerializer(redisSerializer);
        // redisTemplate.setHashKeySerializer(redisSerializer);

        return redisTemplate;
    }
}