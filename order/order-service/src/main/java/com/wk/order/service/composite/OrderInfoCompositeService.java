package com.wk.order.service.composite;


import com.wk.order.entity.ext.OrderInfoExt;

import java.util.List;

public interface OrderInfoCompositeService {

    List<OrderInfoExt> findOrderList();

    void addOrder(OrderInfoExt orderInfo);
}
