package com.wk.product.service.base;

import com.wk.entity.exception.BusinessSeataException;
import com.wk.product.entity.base.ProductStock;

public interface IProductStockService {

    ProductStock getProductStock(String productId);

    /**
     * 冻结库存.
     * @param productId
     * @param num
     */
    void commitFreezeStock(String productId, int num) throws BusinessSeataException;

    /**
     * 扣减库存.
     * @param productId
     * @param num
     */
    void commitSubStock(String productId, int num) throws BusinessSeataException;

    /**
     * 增加库存.
     * @param productId
     * @param num
     */
    void commitAddStock(String productId, int num) throws BusinessSeataException;
}
