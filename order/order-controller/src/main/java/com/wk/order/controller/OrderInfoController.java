package com.wk.order.controller;


import com.wk.order.entity.base.OrderInfo;
import com.wk.order.entity.ext.OrderInfoExt;
import com.wk.order.service.base.OrderInfoService;
import com.wk.order.service.composite.OrderInfoCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/1/31 下午5:37
 * @description: 订单入口
 */
@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {

    @Value("${spring.redis.host}")
    private String hostname;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderInfoCompositeService orderInfoCompositeService;


    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    public String getConfig() {
        return "参数:" + hostname;
    }

    @RequestMapping(value = "/findOrderList", method = RequestMethod.GET)
    public List<OrderInfo> findOrderList() {
        List<OrderInfo> orderList = orderInfoService.findOrderList();
        return orderList;
    }

    @RequestMapping(value = "/findOrderDetailList", method = RequestMethod.GET)
    public List<OrderInfoExt> findOrderDetailList() {
        return orderInfoCompositeService.findOrderList();
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public void addOrder(@RequestBody OrderInfoExt orderInfo) {
        orderInfoCompositeService.addOrder(orderInfo);
    }

}
