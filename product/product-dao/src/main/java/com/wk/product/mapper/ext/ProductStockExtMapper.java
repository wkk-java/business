package com.wk.product.mapper.ext;

import com.wk.product.mapper.base.ProductStockMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductStockExtMapper extends ProductStockMapper {

    int subtractStock(@Param("productId") String productId, @Param("num") int num);

    int freezeStock(@Param("productId") String productId, @Param("num") int num);

    int addStock(@Param("productId") String productId, @Param("num") int num);
}