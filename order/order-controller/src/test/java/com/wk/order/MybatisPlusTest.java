package com.wk.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wk.order.entity.OrderInfo;
import com.wk.order.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class MybatisPlusTest extends JunitApplicationRunner {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void selectOrderInfo() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "zhangsan");
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
        OrderInfo orderInfo = new OrderInfo();
        orderInfoMapper.insert(orderInfo);
        log.info("result:{}", JSONObject.toJSONString(orderInfo));
        //1456498121438482433
        //1456498233514393602
    }

    @Test
    public void deleteOrderInfo() {
        orderInfoMapper.deleteById("1456498121438482433");
        //1456498121438482433
        //1456498233514393602
    }

    @Test
    public void deleteAllOrderInfo() {
        orderInfoMapper.delete(new QueryWrapper<>());
        //1456498121438482433
        //1456498233514393602
    }

    @Test
    public void saveOrderInfo() {
        OrderInfo orderInfos = new OrderInfo();
        orderInfos.setId("1455496872911974401");
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        Map<String, Object> map = BeanUtils.beanToMap(orderInfos);
        wrapper.allEq(map, false);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setName("zhangsan111");
        int update = orderInfoMapper.update(orderInfo, wrapper);
        log.info("size:{},result:{}", update, JSONObject.toJSONString(orderInfo));
    }

    @Test
    public void saveOrderInfoVersion() {
        OrderInfo orderInfo = orderInfoMapper.selectById("1455496872911974401");
        orderInfo.setName("张三你好");
        orderInfoMapper.updateById(orderInfo);
    }

    @Test
    public void selectOrderInfoByPage() {
        Page<OrderInfo> page = new Page<>(1, 3);
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "zhangsan");
        Page<OrderInfo> orderInfoIPage = orderInfoMapper.selectPage(page, queryWrapper);
        log.info("总页数:{}", orderInfoIPage.getPages());
        log.info("查询结果:{}", JSONObject.toJSONString(orderInfoIPage));
    }
}
