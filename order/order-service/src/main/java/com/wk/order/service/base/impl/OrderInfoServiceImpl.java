package com.wk.order.service.base.impl;

import com.wk.order.entity.base.OrderInfo;
import com.wk.order.entity.base.OrderInfoExample;
import com.wk.order.mapper.base.OrderInfoMapper;
import com.wk.order.service.base.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/1/31 下午7:06
 * @description: 订单
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> findOrderList() {
        OrderInfoExample orderInfoExample = new OrderInfoExample();
        return orderInfoMapper.selectByExample(orderInfoExample);
    }

    @Override
    public void addOrder(OrderInfo orderInfo) {
        orderInfoMapper.insertSelective(orderInfo);
    }
}
