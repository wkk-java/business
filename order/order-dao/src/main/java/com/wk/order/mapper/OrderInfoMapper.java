package com.wk.order.mapper;

import com.wk.order.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2021-11-04
 */
@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}
