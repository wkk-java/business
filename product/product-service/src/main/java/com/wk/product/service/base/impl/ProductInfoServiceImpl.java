package com.wk.product.service.base.impl;

import com.wk.product.entity.base.ProductInfo;
import com.wk.product.entity.base.ProductInfoExample;
import com.wk.product.mapper.base.ProductInfoMapper;
import com.wk.product.service.base.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/2/2 下午3:20
 * @description: 产品服务
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo getProductInfo(String productCode) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andCodeEqualTo(productCode);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);
        return CollectionUtils.isEmpty(productInfos) ? null : productInfos.get(0);
    }

    @Override
    public ProductInfo getProductInfoById(String productId) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andIdEqualTo(productId);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);
        return CollectionUtils.isEmpty(productInfos) ? null : productInfos.get(0);
    }

    @Override
    public List<ProductInfo> findProductList(List productIds) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andIdIn(productIds);
        return productInfoMapper.selectByExample(productInfoExample);
    }
}
