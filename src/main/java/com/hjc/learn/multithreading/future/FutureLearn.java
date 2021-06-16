package com.hjc.learn.multithreading.future;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在执行多个任务的时候，使用Java标准库提供的线程池是非常方便的。我们提交的任务只需要实现Runnable接口，就可以让线程池去执行：
 * Runnable接口有个问题，它的方法没有返回值。如果任务需要一个返回结果，那么只能保存到变量，还要提供额外的方法读取，非常不便。
 * 所以，Java标准库还提供了一个Callable接口，和Runnable接口比，它多了一个返回值：
 *
 * 对线程池提交一个Callable任务，可以获得一个Future对象；
 *
 * 可以用Future在将来某个时刻获取结果。
 *
 * @author houjichao
 */
public class FutureLearn {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<BigDecimal> future = es.submit(new Task("601857"));
        // future.isDone(); 轮询看是否为true判断是否执行完成
        System.out.println(future.get());
        es.shutdown();
    }
}

class Task implements Callable<BigDecimal> {

    public Task(String code) {
    }

    @Override
    public BigDecimal call() throws Exception {
        Thread.sleep(1000);
        double d = 5 + Math.random() * 20;
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN);
    }
}