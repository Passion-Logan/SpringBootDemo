package com.cody.mapper;

import com.cody.entity.OrderRecordEntity;

public interface OrderRecordEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRecordEntity record);

    int insertSelective(OrderRecordEntity record);

    OrderRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRecordEntity record);

    int updateByPrimaryKey(OrderRecordEntity record);
}