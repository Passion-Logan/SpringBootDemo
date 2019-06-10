package com.cody.mapper;

import com.cody.entity.OrderReportEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrderReportEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderReportEntity record);

    int insertSelective(OrderReportEntity record);

    OrderReportEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderReportEntity record);

    int updateByPrimaryKey(OrderReportEntity record);
}