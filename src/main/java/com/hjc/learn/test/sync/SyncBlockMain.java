package com.hjc.learn.test.sync;

/**
 * synchronized修饰代码块
 *
 * @author houjichao
 */
public class SyncBlockMain {

    public static void main(String[] args) throws InterruptedException {

        //incr同步方法执行
        SyncBlockTest syncBlockTest = new SyncBlockTest();
        Thread thread = new Thread(() -> syncBlockTest.incr());
        Thread threadTwo = new Thread(() -> syncBlockTest.incr());
        thread.start();
        threadTwo.start();
        thread.join();
        threadTwo.join();
        //最终打印结果是20000
        System.currentTimeMillis();
        System.out.println(syncBlockTest.getJ());

        //incrTwo方法
        thread = new Thread(() -> syncBlockTest.incrTwo());
        threadTwo = new Thread(() -> syncBlockTest.incrTwo());
        thread.start();
        threadTwo.start();
        thread.join();
        threadTwo.join();
        //最终打印结果是40000
        System.out.println(syncBlockTest.getJ());

        /*
         * 先看看incr同步方法执行，流程和前面没区别，
         * 只是Synchronized锁定的范围太大，把syncDbData()也纳入临界区中，
         * 多线程场景执行，会有性能上的浪费，因为syncDbData()完全可以让多线程并行或并发执行。
         */

        /*
         * 我们通过代码块的方式，来缩小范围，定义正确的临界区，提升性能，
         * 目光转到incrTwo同步块执行，incrTwo函数使用修饰代码块的方式同步，只对自增代码段进行锁定。
         */
    }
}
