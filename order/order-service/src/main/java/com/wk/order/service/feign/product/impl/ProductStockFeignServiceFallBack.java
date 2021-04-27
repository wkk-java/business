package com.wk.order.service.feign.product.impl;

import com.wk.order.service.feign.product.ProductStockFeignService;
import com.wk.product.entity.base.ProductStock;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: vince
 * create at: 2021/2/2 下午3:43
 * @description: 降级处理
 */
@Slf4j
@Service
public class ProductStockFeignServiceFallBack implements ProductStockFeignService, FallbackFactory<ProductStockFeignService> {

    private Throwable throwable;

    @Override
    public ProductStockFeignService create(Throwable throwable) {
        this.throwable = throwable;
        return null;
    }

    @Override
    public void freezeProductStock(ProductStock productStock) {
        log.error("冻结库存失败! productStock: {}, \n错误信息:{}", productStock, throwable);
    }

    @Override
    public void subProductStock(ProductStock productStock) {
        log.error("扣减库存失败! productStock: {}, \n错误信息:{}", productStock, throwable);
    }

    @Override
    public void addProductStock(ProductStock productStock) {
        log.error("增加库存失败! productStock: {}, \n错误信息:{}", productStock, throwable);
    }

    @Override
    public ProductStock getProductStock(String productId) {
        log.error("获取库存详情失败! productId: {}, \n错误信息:{}", productId, throwable);
        return null;
    }
}
