package com.wk.product.entity.ext;

import com.wk.product.entity.base.ProductStock;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * @author: vince
 * create at: 2021/3/24 下午10:23
 * @description:
 */
@SuperBuilder
public class ProductStockExt extends ProductStock {

    public static ProductStock buildNew(String productId, int num) {
        ProductStock productStock = ProductStock.builder()
                .id(UUID.randomUUID().toString())
                .num(num).category("book").freezeNum(0).productId(productId).build();
        return productStock;
    }
}
