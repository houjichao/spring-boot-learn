package com.hjc.learn.test.sync;

/**
 * 普通函数与静态函数粒度都比较大，以整个函数为范围锁定，
 * 现在想把范围缩小、灵活配置，就需要使用代码块了，
 * 使用{}符号定义范围给Synchronized修饰。
 *
 * @author houjichao
 */
public class SyncBlockTest {

    private static int j = 0;

    public void syncDbData() {

        System.out.println("db数据开始同步--------");
        try {
            //模拟同步 同步时间需要两秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("db数据同步完成--------");

    }

    /**
     * 自增方法
     */
    public synchronized void incr() {
        //start--临界区代码
        //同步数据库
        syncDbData();
        for (int i = 0; i < 10000; i++) {
            j++;
        }
        //end--临界区代码
    }

    /**
     * 自增方法
     */
    public void incrTwo() {
        //同步库数据
        syncDbData();
        //同步库数据
        synchronized (this) {
            //start--临界区代码
            for (int i = 0; i < 10000; i++) {
                j++;
            }
            //end--临界区代码
        }
    }

    public int getJ() {
        return j;
    }

}
