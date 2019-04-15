package com.cody.service.impl;

import com.cody.dao.entity.DemoEntity;
import com.cody.mapper.DemoEntityMapper;
import com.cody.service.DemoService;
import com.cody.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @File Name: com.cody.service.impl
 * @Author: WQL //作者及
 * @Date: 2019/4/15 11:29//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class DemoServiceImpl implements DemoService
{

    @Resource
    private DemoEntityMapper demoEntityMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public List<DemoEntity> getUsers()
    {
        return demoEntityMapper.getUsers();
    }

    /**
     * 获取逻辑：
     * 如果缓存存在，从缓存中获取信息
     * 如果缓存不存在，从 DB 中获取信息，然后插入缓存
     */
    @Override
    public DemoEntity getById(Integer id)
    {
        String key = "user_" + id;
        ValueOperations<String, DemoEntity> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey)
        {
            DemoEntity demoEntity = (DemoEntity) redisService.get(key);
            LOGGER.info("DemoServiceImpl.getById() : 从缓存中获取了用户 >> " + demoEntity.toString());
            return demoEntity;
        }

        // 从 DB 中获取信息
        DemoEntity demoEntity = demoEntityMapper.selectByPrimaryKey(id);

        // 插入缓存
        redisService.set(key, demoEntity);
        LOGGER.info("DemoServiceImpl.getById() : 插入缓存 >> " + demoEntity.toString());

        return demoEntity;
    }

    @Override
    public int saveUser(DemoEntity demoEntity)
    {
        return demoEntityMapper.insert(demoEntity);
    }

    /**
     * 更新逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     * @param demoEntity
     * @return
     */
    @Override
    public int updateUser(DemoEntity demoEntity)
    {
        int ret = demoEntityMapper.updateByPrimaryKey(demoEntity);

        // 缓存存在，删除缓存
        String key = "user_" + String.valueOf(demoEntity.getId());
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey)
        {
            redisTemplate.delete(key);

            LOGGER.info("DemoServiceImpl.updateUser() : 从缓存中删除信息 >> " + demoEntity.toString());
        }
        return ret;
    }

    /**
     * 删除逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id)
    {
        int ret = demoEntityMapper.deleteByPrimaryKey(id);

        // 缓存存在，删除缓存
        String key = "user_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey)
        {
            redisTemplate.delete(key);

            LOGGER.info("DemoServiceImpl.deleteById() : 从缓存中删除信息ID >> " + id);
        }
        return ret;
    }
}