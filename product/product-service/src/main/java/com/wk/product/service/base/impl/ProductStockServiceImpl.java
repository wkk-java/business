package com.wk.product.service.base.impl;

import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.exception.ExceptionType;
import com.wk.product.entity.base.ProductInfo;
import com.wk.product.entity.base.ProductStock;
import com.wk.product.entity.base.ProductStockExample;
import com.wk.product.entity.ext.ProductStockExt;
import com.wk.product.mapper.ext.ProductStockExtMapper;
import com.wk.product.service.base.IProductInfoService;
import com.wk.product.service.base.IProductStockService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/3/24 下午6:45
 * @description:
 */
@Slf4j
@Service
public class ProductStockServiceImpl implements IProductStockService {

    @Autowired
    private ProductStockExtMapper productStockMapper;
    @Autowired
    private IProductInfoService iProductInfoService;

    @Override
    public ProductStock getProductStock(String productId) {
        ProductStockExample productStockExample = new ProductStockExample();
        productStockExample.createCriteria().andProductIdEqualTo(productId);
        List<ProductStock> productStocks = productStockMapper.selectByExample(productStockExample);
        return CollectionUtils.isEmpty(productStocks) ? null : productStocks.get(0);
    }

    /**
     * 冻结库存.
     *
     * @param productId
     * @param num
     */
    @Transactional
    @Override
    public void commitFreezeStock(String productId, int num) {
        log.info("[commitFreezeStock] 当前 XID: {}", RootContext.getXID());
        int result = productStockMapper.freezeStock(productId, num);
        if (result <= 0) {
            log.error("库存冻结失败,product:{}, num:{}", productId, num);
            throw new BusinessRuntimeException(ExceptionType.REMARK, "库存冻结失败");
        }
    }

    /**
     * 扣减库存.
     *
     * @param productId
     * @param num
     */
    @Override
    public void commitSubStock(String productId, int num) {
        int result = productStockMapper.subtractStock(productId, num);
        if (result <= 0) {
            log.error("库存扣减失败,product:{}, num:{}", productId, num);
            throw new BusinessRuntimeException(ExceptionType.REMARK, "库存扣减失败");
        }
    }

    /**
     * 增加库存.
     *
     * @param productId
     * @param num
     */
    @Override
    public void commitAddStock(String productId, int num) {
        ProductInfo productInfo = iProductInfoService.getProductInfoById(productId);
        if (productInfo == null) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "未找到该产品信息!");
        }
        ProductStock productStock = getProductStock(productId);
        if (productStock == null) {
            productStock = ProductStockExt.buildNew(productId, num);
            productStockMapper.insertSelective(productStock);
        }
        int result = productStockMapper.addStock(productId, num);
        if (result <= 0) {
            log.error("库存增加失败,product:{}, num:{}", productId, num);
            throw new BusinessRuntimeException(ExceptionType.REMARK, "库存增加失败");
        }

    }
}
