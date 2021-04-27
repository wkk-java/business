package com.wk.sys.service.feign;

import com.wk.order.entity.base.OrderInfo;
import com.wk.order.entity.ext.OrderInfoExt;
import com.wk.sys.service.feign.impl.OrderInfoFeignServiceImpl;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RibbonClient()
@FeignClient(value = "order-server", fallbackFactory = OrderInfoFeignServiceImpl.class)
public interface OrderInfoFeignService {

    @GetMapping(value = "/orderInfo/findOrderList")
    List<OrderInfo> findOrderList();

    @GetMapping(value = "/orderInfo/findOrderDetailList")
    List<OrderInfoExt> findOrderDetailList();
}
