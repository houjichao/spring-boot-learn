package com.hjc.learn.util;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Guava StopWatch 计时器
 * 用途：StopWatch 用来计算经过的时间（精确到纳秒）。
 * 这个类比调用 System.nanoTime() 优势在于：
 * 性能
 * 表现形式更丰富
 *
 * @author houjichao
 */
@Slf4j
public class StopWatchUtil {

    public static void main(String[] args) throws Exception {
        /*
         * static Stopwatch	createStarted()
         * 创建启动一个新的stopwatch对象，用的是System.nanoTime()作为时间资源。
         * static Stopwatch	createStarted(Ticker ticker)
         * 创建启动一个新的stopwatch对象，用的是特定的时间资源。
         * static Stopwatch	createUnstarted()
         * 创建（但不启动）一个新的stopwatch对象，用的是System.nanoTime()作为时间资源。
         * static Stopwatch	createUnstarted(Ticker ticker)
         * 创建（但不启动）一个新的stopwatch对象，用的是特定的时间资源。
         * Duration	elapsed()
         * 返回将此秒表上显示的当前经过时间作为持续时间.
         * long	elapsed(TimeUnit desiredUnit)
         * 用特定的格式返回这个stopwatch经过的时间.
         * boolean	isRunning()
         * 如果start方法被调用。stop方法还没有调用。返回真.
         * Stopwatch	reset()
         * 把stopwatch经过的时间设置为零，状态设置为停止.
         * Stopwatch	start()
         * 启动 stopwatch.
         * Stopwatch	stop()
         * 停止stopwatch，读取的话将会返回经历过的时间.
         * String	toString()
         * 返回字符串形式的elapsed time.
         */
        String orderNo = "12345678";

        log.info("订单 [{}] 开始处理", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();

        // 1秒处理时间
        TimeUnit.SECONDS.sleep(1);

        log.info("订单 [{}] 处理完成，耗时 [{}]", orderNo, stopwatch.stop());


        // 创建stopwatch并开始计时
        Stopwatch stopwatchTwo = Stopwatch.createStarted();
        Thread.sleep(1980);

        // 以秒打印从计时开始至现在的所用时间，向下取整
        System.out.println(stopwatchTwo.elapsed(TimeUnit.SECONDS));

        // 停止计时
        stopwatchTwo.stop();
        System.out.println(stopwatchTwo.elapsed(TimeUnit.SECONDS));

        // 再次计时
        stopwatchTwo.start();
        Thread.sleep(100);
        System.out.println(stopwatchTwo.elapsed(TimeUnit.SECONDS));

        // 重置并开始
        stopwatchTwo.reset().start();
        Thread.sleep(1030);

        // 检查是否运行
        System.out.println(stopwatchTwo.isRunning());
        long millis = stopwatchTwo.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(millis);

        // 打印
        System.out.println(stopwatchTwo.toString());
    }
}
