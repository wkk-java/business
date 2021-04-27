package com.wk.order.service.feign.product.impl;

import com.wk.order.service.feign.product.ProductInfoFeignService;
import com.wk.product.entity.base.ProductInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/2/2 下午3:43
 * @description: 降级处理
 */
@Log4j2
@Service
public class ProductInfoFeignServiceFallBack implements ProductInfoFeignService, FallbackFactory<ProductInfoFeignService> {

    private Throwable throwable;

    @Override
    public ProductInfoFeignService create(Throwable throwable) {
        this.throwable = throwable;
        return null;
    }

    @Override
    public ProductInfo getProductInfo(String productCode) {
        log.error("获取产品详情出错");
        return null;
    }

    @Override
    public ProductInfo getProductInfoById(String productId) {
        log.error("获取产品相信出错:{}", productId);
        return null;
    }

    @Override
    public List<ProductInfo> findProductInfo(List<String> productIds) {
        log.error("获取产品列表出错");
        return null;
    }


}
