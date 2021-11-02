package com.wk.order.service.base;


import com.wk.order.entity.base.OrderInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface OrderInfoService {

    List<OrderInfo> findOrderList();

    void addOrder(OrderInfo orderInfo);

    void testAsync() throws InterruptedException, ExecutionException;

    void asyncDoSomething() throws Exception;


}
