package com.example.demomybatis.mapper;

import com.example.demomybatis.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @File Name: com.example.demomybatis.mapper
 * @Author: WQL //作者及
 * @Date: 2019/1/8 13:27//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public interface UserMapper {

    List<UserEntity> getAll();

    UserEntity getOne(int id);

    void insert(UserEntity userEntity);

    void update(UserEntity userEntity);

    void delete(int id);
}
