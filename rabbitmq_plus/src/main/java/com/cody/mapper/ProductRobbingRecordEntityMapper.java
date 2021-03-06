package com.cody.mapper;

import com.cody.entity.ProductRobbingRecordEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProductRobbingRecordEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRobbingRecordEntity record);

    int insertSelective(ProductRobbingRecordEntity record);

    ProductRobbingRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRobbingRecordEntity record);

    int updateByPrimaryKey(ProductRobbingRecordEntity record);
}