package com.wk.order.service.feign.product;

import com.wk.product.entity.base.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-server/productInfo")
public interface ProductInfoFeignService {

    @RequestMapping(value = "/getProductInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductInfo getProductInfo(@RequestParam("productCode") String productCode);

    @RequestMapping(value = "/getProductInfoById", method = RequestMethod.GET)
    ProductInfo getProductInfoById(@RequestParam("productId") String productId);

    @RequestMapping(value = "/findProductList", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    List<ProductInfo> findProductInfo(@RequestBody List<String> productIds);


}
