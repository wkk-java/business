package com.wk.product.mapper.base;

import com.wk.product.entity.base.ProductStock;
import com.wk.product.entity.base.ProductStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ProductStockMapper {
    long countByExample(ProductStockExample example);

    int deleteByExample(ProductStockExample example);

    int insert(ProductStock record);

    int insertSelective(ProductStock record);

    List<ProductStock> selectByExampleWithRowbounds(ProductStockExample example, RowBounds rowBounds);

    List<ProductStock> selectByExample(ProductStockExample example);

    int updateByExampleSelective(@Param("record") ProductStock record, @Param("example") ProductStockExample example);

    int updateByExample(@Param("record") ProductStock record, @Param("example") ProductStockExample example);
}