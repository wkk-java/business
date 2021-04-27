package com.wk.product.service.base;

import com.wk.product.entity.base.ProductInfo;

import java.util.List;

public interface IProductInfoService {

    ProductInfo getProductInfo(String productCode);

    ProductInfo getProductInfoById(String productId);

    List<ProductInfo> findProductList(List productIds);
}
