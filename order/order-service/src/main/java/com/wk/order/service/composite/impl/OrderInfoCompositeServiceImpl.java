package com.wk.order.service.composite.impl;

import com.wk.order.entity.base.OrderInfo;
import com.wk.order.entity.ext.OrderInfoExt;
import com.wk.order.service.base.OrderInfoService;
import com.wk.order.service.composite.OrderInfoCompositeService;
import com.wk.order.service.feign.product.ProductInfoFeignService;
import com.wk.order.service.feign.product.ProductStockFeignService;
import com.wk.order.service.feign.sys.UserAccountFeignService;
import com.wk.product.entity.base.ProductInfo;
import com.wk.product.entity.base.ProductStock;
import com.wk.sys.entity.ext.UserAccountExt;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: vince
 * create at: 2021/2/2 下午4:11
 * @description:
 */
@Slf4j
@Service
public class OrderInfoCompositeServiceImpl implements OrderInfoCompositeService {

    @Autowired
    private ProductInfoFeignService productInfoFeignService;

    @Autowired
    private ProductStockFeignService productStockFeignService;

    @Autowired
    private UserAccountFeignService userAccountFeignService;
    @Autowired
    private OrderInfoService orderInfoService;

    @GlobalTransactional
    @Override
    public List<OrderInfoExt> findOrderList() {
        List<OrderInfo> orderList = orderInfoService.findOrderList();
        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }
        //抽取productId集合
        List<String> productIdList = orderList.stream().map(obj -> obj.getProductId()).collect(Collectors.toList());
        //调用product-feign
        List<ProductInfo> productInfoList = productInfoFeignService.findProductInfo(productIdList);
        //list转map
        Map<String, ProductInfo> productInfoMap = productInfoList.stream().collect(Collectors.toMap(obj -> obj.getId(), obj -> obj));
        //返回结果
        List<OrderInfoExt> orderExtList = new ArrayList<>(orderList.size());
        orderList.stream().forEach(obj -> {
            OrderInfoExt orderInfoExt = OrderInfoExt.builder().build();
            BeanUtils.copyProperties(obj, orderInfoExt);
            orderInfoExt.setProductInfo(productInfoMap.get(obj.getProductId()));
            orderExtList.add(orderInfoExt);
        });
        return orderExtList;
    }

    @Transactional
    @Override
    public void addOrder(OrderInfoExt orderInfo) {
        log.info("[addOrder] 当前 XID: {}", RootContext.getXID());
        orderInfo.validateNewOrder();

        //查询计算产品价格
        ProductInfo productInfo = productInfoFeignService.getProductInfoById(orderInfo.getProductId());
        BigDecimal needMoney = productInfo.getPrice().multiply(new BigDecimal(orderInfo.getProductNum()));
        //冻结资金
        UserAccountExt userAccount = UserAccountExt.builder().money(needMoney).userId(orderInfo.getUserId()).build();
        userAccountFeignService.freezeMoney(userAccount);
        //扣减库存
        ProductStock productStock = ProductStock.builder().productId(orderInfo.getProductId()).freezeNum(orderInfo.getProductNum()).build();
        productStockFeignService.freezeProductStock(productStock);
        orderInfo.setPrice(needMoney.toString());
        orderInfo.setId(UUID.randomUUID().toString());
        orderInfoService.addOrder(orderInfo);

    }
}