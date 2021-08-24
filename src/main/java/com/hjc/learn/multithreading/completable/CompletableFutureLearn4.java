package com.hjc.learn.multithreading.completable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * 回顾整个CompletableFuture的用法主要可概括为以下几点
 * <p>
 * 简单用法 get() 与 complete()
 * 提交任务 runAsync() 与 supplyAsync()
 * 链式处理 theRun()、thenAccept() 和 thenApply()
 * 组合处理 thenCompose() 与 thenCombine() 、allOf与anyOf()
 *
 * @author houjichao
 */
@Slf4j
public class CompletableFutureLearn4 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //在这里我们可以将对各future实例添加到allOf方法中，然后通过future的get（）方法获取future的状态。
        //如果allOf里面的所有线程为执行完毕，主线程会阻塞，直到allOf里面的所有线程都执行，线程就会被唤醒。
        CompletableFuture.allOf(CompletableFuture.runAsync(CompletableFutureLearn4::queryOne),
                CompletableFuture.runAsync(CompletableFutureLearn4::queryTwo)).join();
        long end = System.currentTimeMillis();
        log.info("CompletableFutureLearn4 allOf cast time:{}", end - start);


        //anyOf 的含义是只要有任意一个 CompletableFuture 结束，就可以做 接下来的事情，而无须像 AllOf 那样，等待所有的 CompletableFuture 结束。
        //但由于每个 CompletableFuture 的返回值类型都可能不同，任意一个， 意味着无法判断是什么类型，所以 anyOf 的返回值是 CompletableFuture<Object>类型。 考虑下面的例子。
        start = System.currentTimeMillis();
        CompletableFuture.anyOf(CompletableFuture.runAsync(CompletableFutureLearn4::queryOne),
                CompletableFuture.runAsync(CompletableFutureLearn4::queryTwo)).join();
        end = System.currentTimeMillis();
        log.info("CompletableFutureLearn4 anyOf cast time:{}", end - start);
    }

    static void queryOne() {
        try {
            log.info("queryOne start");
            long start = System.currentTimeMillis();
            Thread.sleep((long) (Math.random() * 10000));
            long end = System.currentTimeMillis();
            log.info("queryOne end:{}", end - start);
        } catch (InterruptedException e) {
            log.error("");
        }
    }

    static void queryTwo() {
        try {
            log.info("queryTwo start");
            long start = System.currentTimeMillis();
            Thread.sleep((long) (Math.random() * 10000));
            long end = System.currentTimeMillis();
            log.info("queryTwo end:{}", end - start);
        } catch (InterruptedException e) {
            log.error("");
        }
    }

}
