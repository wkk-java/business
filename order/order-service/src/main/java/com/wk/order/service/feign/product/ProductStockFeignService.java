package com.wk.order.service.feign.product;


import com.wk.product.entity.base.ProductStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-server/productStock")
public interface ProductStockFeignService {

    @RequestMapping(value = "/freezeProductStock", method = RequestMethod.POST)
    void freezeProductStock(@RequestBody ProductStock productStock);

    @RequestMapping(value = "/subProductStock", method = RequestMethod.POST)
    void subProductStock(@RequestBody ProductStock productStock);

    @RequestMapping(value = "/addProductStock", method = RequestMethod.POST)
    void addProductStock(@RequestBody ProductStock productStock);

    @RequestMapping(value = "/getProductStock", method = RequestMethod.GET)
    ProductStock getProductStock(@RequestParam("productId") String productId);

}
