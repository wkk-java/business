package com.wk.product.controller;

import com.wk.product.entity.base.ProductInfo;
import com.wk.product.service.base.IProductInfoService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/2/2 下午2:36
 * @description: 产品入口
 */
@RestController
@RequestMapping(value = "/productInfo")
public class ProductInfoController {

    @Autowired
    private IProductInfoService productInfoService;

    @GlobalTransactional
    @RequestMapping(value = "/getProductInfo", method = RequestMethod.GET)
    public ProductInfo getProductInfo(@RequestParam("productCode") String productCode) {
        return productInfoService.getProductInfo(productCode);
    }

    @GlobalTransactional
    @RequestMapping(value = "/getProductInfoById", method = RequestMethod.GET)
    public ProductInfo getProductInfoById(@RequestParam("productId") String productId) {
        return productInfoService.getProductInfoById(productId);
    }

    @GlobalTransactional
    @RequestMapping(value = "/findProductList", method = RequestMethod.POST)
    public List<ProductInfo> findProductInfo(@RequestBody List<String> productIds) {
        return productInfoService.findProductList(productIds);
    }
}
