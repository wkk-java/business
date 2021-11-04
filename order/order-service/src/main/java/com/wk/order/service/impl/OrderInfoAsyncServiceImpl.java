package com.wk.order.service.impl;

import com.wk.order.service.OrderInfoAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class OrderInfoAsyncServiceImpl implements OrderInfoAsyncService {

    @Override
    public void testAsync() throws InterruptedException, ExecutionException {
        log.info("同步执行{}开始", Thread.currentThread().getName());
//        Thread.sleep(3000);
        Future<Map<String, String>> mapFuture = asyncDoSomething();
        Thread.sleep(3000);
        while (!mapFuture.isDone()) {
            Thread.sleep(50);
        }
        Map<String, String> map = mapFuture.get();
        log.info("结果:{}", map);
        log.info("同步执行{}结束", Thread.currentThread().getName());
    }

    @Async
    @Override
    public Future<Map<String, String>> asyncDoSomething() throws InterruptedException {
        log.info("异步执行{}开始", Thread.currentThread().getName());
        Thread.sleep(3000);
        Map<String, String> result = new HashMap<>();
        result.put("code", "success");
        result.put("msg", "成功");
        log.info("异步执行{}结束", Thread.currentThread().getName());
        return new AsyncResult<Map<String, String>>(result);
    }
}
