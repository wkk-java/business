package com.wk.order.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface OrderInfoAsyncService {
    void testAsync() throws InterruptedException, ExecutionException;

    Future<Map<String, String>> asyncDoSomething() throws InterruptedException;
}
