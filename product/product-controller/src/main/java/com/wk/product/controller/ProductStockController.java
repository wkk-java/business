package com.wk.product.controller;

import com.wk.product.entity.base.ProductStock;
import com.wk.product.service.base.IProductStockService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: vince
 * create at: 2021/2/2 下午2:36
 * @description: 产品入口
 */
@RestController
@RequestMapping(value = "/productStock")
public class ProductStockController {

    @Autowired
    private IProductStockService iProductStockService;

    @GlobalTransactional
    @RequestMapping(value = "/freezeProductStock", method = RequestMethod.POST)
    public void freezeProductStock(@RequestBody ProductStock productStock) {
        iProductStockService.commitFreezeStock(productStock.getProductId(), productStock.getFreezeNum());
    }

    @GlobalTransactional
    @RequestMapping(value = "/subProductStock", method = RequestMethod.POST)
    public void subProductStock(@RequestBody ProductStock productStock) {
        iProductStockService.commitSubStock(productStock.getProductId(), productStock.getNum());
    }

    @GlobalTransactional
    @RequestMapping(value = "/addProductStock", method = RequestMethod.POST)
    public void addProductStock(@RequestBody ProductStock productStock) {
        iProductStockService.commitAddStock(productStock.getProductId(), productStock.getNum());
    }

    @GlobalTransactional
    @RequestMapping(value = "/getProductStock", method = RequestMethod.GET)
    public ProductStock getProductStock(@RequestParam("productId") String productId) {
        return iProductStockService.getProductStock(productId);
    }

}
