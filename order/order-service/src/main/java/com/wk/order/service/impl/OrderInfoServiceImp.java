package com.wk.order.service.impl;

import com.wk.order.entity.OrderInfo;
import com.wk.order.mapper.OrderInfoMapper;
import com.wk.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wk
 * @since 2021-11-05
 */
@Service
public class OrderInfoServiceImp extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
