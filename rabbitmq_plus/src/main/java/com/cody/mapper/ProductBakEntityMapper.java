package com.cody.mapper;

import com.cody.entity.ProductBakEntity;

public interface ProductBakEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBakEntity record);

    int insertSelective(ProductBakEntity record);

    ProductBakEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBakEntity record);

    int updateByPrimaryKey(ProductBakEntity record);
}