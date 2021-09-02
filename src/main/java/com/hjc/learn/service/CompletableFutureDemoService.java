package com.hjc.learn.service;

/**
 * @author houjichao
 */
public interface CompletableFutureDemoService {


    /**
     * thenRun* 方法：不消费CompletableFuture对象的结果，执行一个新任务
     */
    void thenRunDemo() throws Exception;

}
