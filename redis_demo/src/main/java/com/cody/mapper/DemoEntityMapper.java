package com.cody.mapper;

import com.cody.dao.entity.DemoEntity;

import java.util.List;

public interface DemoEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DemoEntity record);

    int insertSelective(DemoEntity record);

    DemoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemoEntity record);

    int updateByPrimaryKey(DemoEntity record);

    List<DemoEntity> getUsers();

}