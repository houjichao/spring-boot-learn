package com.hjc.learn.service.impl;

import com.hjc.learn.service.ThreadPoolDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 这里解释一下为什么要使用自定义的Executor
 * 为什么要加入自定义的executor而不是采用默认的ForkJoinPool线程池？因为对于IO频繁的数据采用ForkJoinPool不做限制，
 * 如果IO耗时严重，将导致ForkJoinPool资源耗尽，而整个JVM就这一个ForkJoinPool线程池，这样整个JVM的内存可能被耗尽导致OOM,
 * 因此我们针对IO密集型的并行计算我们尽肯能的采用自定义的线程池，
 * 假如资源耗尽线程堆满造成的影响无非是这快逻辑阻塞，但是并不会导致其他接口资源耗尽，这本质上是一种资源隔离的思想；
 *
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

    /**
     * CompletableFuture.thenAcceptBoth*方法
     * <p>
     * thenAcceptBoth* 方法：在两个CompletableFuture对象的执行完后执行。
     * 它和 thenAccept 一样，都是纯消费，但是thenAccept*只能消费一个CompletableFuture对象，
     * 而thenAcceptBoth* 能在两个不同的CompletableFuture对象执行完成后，消费他们两个的计算结果。
     * **而且他仅仅在原始的两个CompletableFuture对象都计算成功之后，才开始执行。**
     * <p>
     * public <U> CompletableFuture<Void> 	thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
     * public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
     * public <U> CompletableFuture<Void> 	thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
     * <p>
     * //runAfterBoth和上面三个的区别就是它不消费原始的CompletableFuture结果
     * public     CompletableFuture<Void> 	runAfterBoth(CompletionStage<?> other,  Runnable action)
     */

    /**
     * 创建CompletableFuture对象
     * <p>
     * //比较特殊，他入参就是返回值，也就是说他可以用来执行需要其他返回值的异步任务。
     * public static <U> CompletableFuture<U> completedFuture(U value)
     * <p>
     * //无返回值，使用默认线程池
     * public static CompletableFuture<Void> 	runAsync(Runnable runnable)
     * <p>
     * //无返回值，使用自定义线程池
     * public static CompletableFuture<Void> 	runAsync(Runnable runnable, Executor executor)
     * <p>
     * //有返回值，使用默认线程池
     * public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier)
     * <p>
     * //有返回值，使用自定义线程池
     * public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier, Executor executor)
     * <p>
     * //
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
     */
    @Override
    public void completableFutureTestOne() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::getMoreData, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(this::getMoreData, executor);
        future.thenAcceptBothAsync(future2, (x, y) -> {
            log.info("future1 和 future都执行完成了，结果分别是：" + x + "," + y);
            log.info("completableFutureTestOne thread name:{}", Thread.currentThread().getName());
        }, executor);
        System.out.println("执行到最后一段代码了，future result：" + future.get());
        System.out.println("执行到最后一段代码了，future2 result: " + future2.get());
    }

    //计算逻辑
    private static Random random = new Random();
    private static long time = System.currentTimeMillis();

    private int getMoreData() {
        log.info("getMoreData thread name:{}", Thread.currentThread().getName());
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to compute, passed:" + System.currentTimeMillis());
        return random.nextInt(1000);
    }

    private int throwException() {
        System.out.println("准备抛出异常");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("抛了");
        throw new RuntimeException("主动抛出异常");
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
