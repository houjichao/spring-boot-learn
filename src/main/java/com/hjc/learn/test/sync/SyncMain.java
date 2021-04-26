package com.hjc.learn.test.sync;

/**
 * Sync synchronized 修饰普通函数
 *
 * @author houjichao
 */
public class SyncMain {

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        Thread thread = new Thread(() -> syncTest.incr());
        Thread threadTwo = new Thread(() -> syncTest.incr());
        thread.start();
        threadTwo.start();
        thread.join();
        threadTwo.join();
        //最终打印结果是20000，如果不使用synchronized修饰，就会导致线程安全问题，输出不确定结果
        System.out.println(syncTest.getJ());
    }
}
