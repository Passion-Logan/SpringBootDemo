package com.cody.mapper;

import com.cody.entity.UserOrderEntity;

public interface UserOrderEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrderEntity record);

    int insertSelective(UserOrderEntity record);

    UserOrderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrderEntity record);

    int updateByPrimaryKey(UserOrderEntity record);
}