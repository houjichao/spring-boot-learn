package com.hjc.learn.demo.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author houjichao
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        int threadNumber = 100;
        //参数为线程个数
        final CountDownLatch cdl = new CountDownLatch(threadNumber);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                int num = 1000;
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    s.append("Java");
                }
                //此方法是CountDownLatch的线程数-1
            });
            t.start();
            cdl.countDown();
            System.out.println(cdl.getCount());
            System.out.println(t.getName() + "t Over");
        }


        long start = System.currentTimeMillis();
        System.out.println("start = " + start);

        //线程启动后调用countDownLatch方法
        //需要捕获异常，当其中线程数为0时这里才会继续运行
        //cdl.await();
        if (cdl.getCount() == 90) {
            long end = System.currentTimeMillis();
            System.out.println("end = " + end);
            System.out.println("end - start = " + (end - start));
        }

    }
}
