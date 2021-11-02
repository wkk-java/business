package com.wk.order.service.base.impl;

import com.wk.order.entity.base.OrderInfo;
import com.wk.order.entity.base.OrderInfoExample;
import com.wk.order.mapper.base.OrderInfoMapper;
import com.wk.order.service.base.OrderInfoAsyncService;
import com.wk.order.service.base.OrderInfoService;
import io.netty.util.concurrent.CompleteFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: vince
 * create at: 2021/1/31 下午7:06
 * @description: 订单
 */
@Async
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderInfoAsyncService orderInfoAsyncService;

    @Override
    public List<OrderInfo> findOrderList() {
        OrderInfoExample orderInfoExample = new OrderInfoExample();
        return orderInfoMapper.selectByExample(orderInfoExample);
    }

    @Override
    public void addOrder(OrderInfo orderInfo) {
        orderInfoMapper.insertSelective(orderInfo);
    }


    @Override
    public void testAsync() throws InterruptedException, ExecutionException {
        orderInfoAsyncService.testAsync();
    }

    @Override
    public void asyncDoSomething() throws Exception {

        log.info("同步执行{}开始", Thread.currentThread().getName());
        Future<Map<String, String>> mapFuture = orderInfoAsyncService.asyncDoSomething();
        Thread.sleep(3000);
        while (!mapFuture.isDone()) {
            Thread.sleep(500);
        }
        Map<String, String> map = mapFuture.get();
        log.info("结果:{}", map);
        log.info("同步执行{}结束", Thread.currentThread().getName());

    }
}
