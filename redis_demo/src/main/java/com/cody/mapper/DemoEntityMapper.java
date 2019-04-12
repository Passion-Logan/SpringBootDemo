package com.cody.mapper;

import com.cody.entity.DemoEntity;

public interface DemoEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DemoEntity record);

    int insertSelective(DemoEntity record);

    DemoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemoEntity record);

    int updateByPrimaryKey(DemoEntity record);
}