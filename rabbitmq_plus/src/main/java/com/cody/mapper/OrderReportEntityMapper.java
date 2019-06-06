package com.cody.mapper;

import com.cody.entity.OrderReportEntity;

public interface OrderReportEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderReportEntity record);

    int insertSelective(OrderReportEntity record);

    OrderReportEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderReportEntity record);

    int updateByPrimaryKey(OrderReportEntity record);
}