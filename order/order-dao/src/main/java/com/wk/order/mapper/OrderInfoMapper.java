package com.wk.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wk.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2021-11-05
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}
