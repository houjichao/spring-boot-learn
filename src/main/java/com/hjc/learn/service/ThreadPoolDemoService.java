package com.hjc.learn.service;

/**
 * @author houjichao
 */
public interface ThreadPoolDemoService {


    /**
     * 多线程并发测试
     */
    void threadPoolTestOne();


    /**
     * CompletableFuture.thenAcceptBoth*方法
     *
     * @throws Exception 异常信息
     */
    void completableFutureTestOne() throws Exception;

}
