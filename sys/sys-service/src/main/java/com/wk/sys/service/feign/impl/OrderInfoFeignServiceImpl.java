package com.wk.sys.service.feign.impl;

import com.wk.order.entity.OrderInfo;
import com.wk.order.entity.ext.OrderInfoExt;
import com.wk.sys.service.feign.OrderInfoFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class OrderInfoFeignServiceImpl implements FallbackFactory<OrderInfoFeignService>, OrderInfoFeignService {


    @Override
    public List<OrderInfo> findOrderList() {
        log.error("查找订单列表出错了"+ cause);
        return null;
    }

    @Override
    public List<OrderInfoExt> findOrderDetailList() {
        log.error("查找订单详细列表出错了"+ cause);
        return null;
    }

    @Override
    public OrderInfoFeignService create(Throwable throwable) {
        this.cause = throwable;
        return this;
    }

    private Throwable cause;


}
