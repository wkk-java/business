package com.wk.order;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.wk.order.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: vince
 * create at: 2021/2/22 上午10:45
 * @description: test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLearning {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final static String orderId = "o_123";

    @Before
    public void init() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(objectJackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

    }

    private OrderInfo getBean(String orderId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(orderId);
        orderInfo.setCrtBy("wk");
        orderInfo.setCrtTime(LocalDateTime.now());
        orderInfo.setPrice("56.555");
        return orderInfo;
    }

    @Test
    public void testString() {
        String redisKey = "order:string:id:" + orderId;
        OrderInfo bean = getBean("123");
        stringRedisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(bean));


    }

    @Test
    public void testHash() {
        String redisKey = "order:hash:" + orderId;
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("id", orderId);
        mapInfo.put("name", "订单1");
        mapInfo.put("price", 56.555);
        mapInfo.put("updateTime", new Date());
        mapInfo.put("createTime", new Date());
        HashOperations opsForHash = redisTemplate.opsForHash();
        opsForHash.putAll(redisKey, mapInfo);

        log.info("redis hash存入对象:{}", mapInfo);
        Map entries = redisTemplate.opsForHash().entries(redisKey);
        log.info("redis hash取出对象:{}", entries);

        Map<String, OrderInfo> beanMap = new HashMap<>();
        OrderInfo orderInfo = getBean("123");
        beanMap.put(orderId, orderInfo);
        beanMap.put("1234", getBean("1234"));
        opsForHash.putAll(redisKey, beanMap);

        log.info("redis hash存入对象:{}", JSONObject.toJSONString(beanMap));
        Map<String, OrderInfo> obj = opsForHash.entries(redisKey);
        log.info("redis hash取出对象:{}", obj);
        OrderInfo orderInfo1 = obj.get(orderId);
        log.info("redis hash取出对象价格:{},日期:{}", orderInfo1.getPrice(), orderInfo1.getCrtTime());
    }

    @Test
    public void testList() {
        List<String> listStr = new ArrayList<>();
        listStr.add("123");
        listStr.add("1233");
        listStr.add("1234");
        redisTemplate.opsForList().rightPushAll("roles:list:r1", listStr);

//        while (true) {
//            Object objTemp = redisTemplate.opsForList().leftPop("user:roles:r1");
//            if (objTemp != null) {
//                log.info("test redis list:{}", objTemp);
//            }
//        }

    }

    @Test
    public void testSet() {
        String redisKey = "roles:set:r1";
        List<String> listStr = new ArrayList<>();
        listStr.add("123");
        listStr.add("1233");
        listStr.add("1234");
        listStr.add("1234");
        listStr.add("1234");
        listStr.stream().forEach(str -> {
            redisTemplate.opsForSet().add(redisKey, str);
        });

        Set members = redisTemplate.opsForSet().members(redisKey);
        log.info("redis set test:{}", members);

        List pop = redisTemplate.opsForSet().pop(redisKey, 3000);
        log.info("redis set test pop:{}", pop);
    }

    @Test
    public void testZSet() {
        String redisKey = "roles:zset:r1";
        List<OrderInfo> listStr = new ArrayList<>();
        listStr.add(getBean("123"));
        listStr.add(getBean("123"));
        listStr.add(getBean("124"));
        listStr.add(getBean("125"));

        log.info("size:{}", listStr.size());

        listStr.stream().forEach(obj -> {
            redisTemplate.opsForSet().add(redisKey, obj);
        });

        Set members = redisTemplate.opsForSet().members(redisKey);
        log.info("redis set test:{}", members);
    }

    @Test
    public void testBitmap() {
        for (int i = 0; i < 30; i++) {
            redisTemplate.opsForValue().setBit("sign:user:u_001", i, i > 10 && (i % 3 == 0 || i % 2 == 0));
            System.out.print((i > 10 && (i % 3 == 0 || i % 2 == 0)) ? 1 : 0);
        }
        System.out.println();

        Object count = redisTemplate.execute((RedisCallback<Long>) conn -> conn.bitCount("sign:user:u_001".getBytes()));
        log.info("redis bitmap count test:{}", count);

        //找出首次出现的位置
        Object offset = redisTemplate.execute((RedisCallback<Long>) conn -> conn.bitPos("sign:user:u_001".getBytes(), true));
        log.info("redis bitmap pos test:{}", offset);

        Object execute = redisTemplate.execute((RedisCallback<List<Long>>) conn -> conn.bitField("sign:user:u_001".getBytes(),
                BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.signed(1)).valueAt(50)));
        log.info("redis bitmap field test:{}", execute);
    }

    @Test
    public void testHyperLogLog() {
        redisTemplate.opsForHyperLogLog().add("doc:d001:read", "user1");
        redisTemplate.opsForHyperLogLog().add("doc:d001:read", "user2");
        redisTemplate.opsForHyperLogLog().add("doc:d001:read", "user3");
        Long size = redisTemplate.opsForHyperLogLog().size("doc:d001:read");
        log.info("redis hyperLog test:{}", size);
    }

    @Test
    public void testBloom() {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100000, 0.01);
        //bloomFilter.put("10086");
        log.info("redis bloom test:{}", bloomFilter.mightContain("123456"));
        log.info("redis bloom test:{}", bloomFilter.mightContain("10086"));
    }

}
