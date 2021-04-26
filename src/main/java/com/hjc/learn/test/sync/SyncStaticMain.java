package com.hjc.learn.test.sync;

/**
 * Sync synchronized 修饰静态函数
 *
 * @author houjichao
 */
public class SyncStaticMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> SyncStaticTest.incr());
        Thread threadTwo = new Thread(() -> SyncStaticTest.incr());
        thread.start();
        threadTwo.start();
        thread.join();
        threadTwo.join();
        //最终打印结果是20000，如果不使用synchronized修饰，就会导致线程安全问题，输出不确定结果
        System.out.println(SyncStaticTest.getJ());
    }
}
