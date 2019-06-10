package com.cody.mapper;

import com.cody.entity.UserLogEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserLogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLogEntity record);

    int insertSelective(UserLogEntity record);

    UserLogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLogEntity record);

    int updateByPrimaryKey(UserLogEntity record);
}