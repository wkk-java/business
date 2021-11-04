package com.wk.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.wk.order.entity.OrderInfo;
import com.wk.order.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@MapperScan("com.wk.order.mapper")
public class MybatisPlusTest extends JunitApplicationRunner {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void selectOrderInfo() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张三");
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(queryWrapper);
        log.info("result:{}", JSONObject.toJSONString(orderInfos));
    }

    @Test
    public void getOrderInfo() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张三");
        OrderInfo orderInfos = orderInfoMapper.selectById("1e26bc06-7cec-417e-b00c-7d7d2983a1a1");
        log.info("result:{}", JSONObject.toJSONString(orderInfos));
    }

    @Test
    public void addOrderInfo() {
        OrderInfo orderInfo = new OrderInfo()
                .setCrtTime(LocalDateTime.now());
        orderInfoMapper.insert(orderInfo);
        log.info("result:{}", JSONObject.toJSONString(orderInfo));
    }

    @Test
    public void saveOrderInfo() {
        OrderInfo orderInfos = new OrderInfo();
        orderInfos.setName("zhangsan");
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        Map<String, Object> map = BeanUtils.beanToMap(orderInfos);
        wrapper.allEq(map, false);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setName("zhangsan111");
        int update = orderInfoMapper.update(orderInfo, wrapper);
        log.info("size:{},result:{}", update, JSONObject.toJSONString(orderInfo));
    }
}
