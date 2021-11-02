package com.wk.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.wk.order.entity.base.OrderInfo;
import com.wk.order.mapper.base.OrderInfoPlusMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@MapperScan("com.wk.order.mapper.base")
public class MybatisPlusTest extends  JunitApplicationRunner{

    @Autowired
    private OrderInfoPlusMapper orderInfoPlusMapper;

    @Test
    public void selectOrderInfo() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张三");
        List<OrderInfo> orderInfos = orderInfoPlusMapper.selectList(queryWrapper);
        log.info("result:{}", JSONObject.toJSONString(orderInfos));
    }

    @Test
    public void getOrderInfo() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张三");
        OrderInfo orderInfos = orderInfoPlusMapper.selectById("1e26bc06-7cec-417e-b00c-7d7d2983a1a1");
        log.info("result:{}", JSONObject.toJSONString(orderInfos));
    }

    @Test
    public void addOrderInfo() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfoPlusMapper.insert(orderInfo);
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
        int update = orderInfoPlusMapper.update(orderInfo, wrapper);
        log.info("size:{},result:{}", update, JSONObject.toJSONString(orderInfo));
    }
}
