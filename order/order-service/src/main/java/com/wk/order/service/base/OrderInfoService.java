package com.wk.order.service.base;


import com.wk.order.entity.base.OrderInfo;

import java.util.List;

public interface OrderInfoService {

    List<OrderInfo> findOrderList();

    void addOrder(OrderInfo orderInfo);
}
