package com.cody.mapper;

import com.cody.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

public interface ProductEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);

    ProductEntity selectByProductNo(@Param("productNo") String productNo);

    int updateTotal(ProductEntity record);
}