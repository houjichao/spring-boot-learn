package com.hjc.learn.service.impl;

import com.hjc.learn.service.ThreadPoolDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author houjichao
 */
@Service
@Slf4j
public class ThreadPoolDemoServiceImpl implements ThreadPoolDemoService {

    @Resource(name = "myExecutor")
    Executor executor;

    @Override
    public void threadPoolTestOne() {
        CompletableFuture.allOf(CompletableFuture.runAsync(this::queryOne, executor), CompletableFuture.runAsync(this::queryTwo, executor)).join();
    }

    private void queryOne() {
        try {
            log.info("ThreadPoolDemoServiceImpl queryOne thread name:{}", Thread.currentThread().getName());
            log.info("ThreadPoolDemoServiceImpl queryOne start");
            long start = System.currentTimeMillis();
            Thread.sleep((long) (Math.random() * 10000));
            long end = System.currentTimeMillis();
            log.info("ThreadPoolDemoServiceImpl queryOne end:{}", end - start);
        } catch (InterruptedException e) {
            log.error("");
        }
    }

    private void queryTwo() {
        try {
            log.info("ThreadPoolDemoServiceImpl queryTwo thread name:{}", Thread.currentThread().getName());
            log.info("ThreadPoolDemoServiceImpl queryTwo start");
            long start = System.currentTimeMillis();
            Thread.sleep((long) (Math.random() * 10000));
            long end = System.currentTimeMillis();
            log.info("ThreadPoolDemoServiceImpl queryTwo end:{}", end - start);
        } catch (InterruptedException e) {
            log.error("");
        }
    }
}
