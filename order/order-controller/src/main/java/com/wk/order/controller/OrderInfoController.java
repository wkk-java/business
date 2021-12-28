package com.wk.order.controller;


import com.wk.entity.result.ResultView;
import com.wk.order.entity.OrderInfo;
import com.wk.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author wk
 * @since 2021-11-05
 */
@Api(tags = "订单信息")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "获取账户信息")
    @GetMapping(value = "/getOrderInfo")
    public ResultView<OrderInfo> getOrderInfo(@RequestHeader("userId") String userId,
                                              @RequestParam(value = "id") String id) {
        OrderInfo orderInfo = orderInfoService.getById(id);
        return ResultView.success(orderInfo);
    }

}

