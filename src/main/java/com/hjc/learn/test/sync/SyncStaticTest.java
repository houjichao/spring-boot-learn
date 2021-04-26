package com.hjc.learn.test.sync;

/**
 * 修饰静态静态函数，视器锁（monitor）便是对象的Class实例（每个对象只有一个Class实例）
 *
 * @author houjichao
 */
public class SyncStaticTest {

    private static int j = 0;

    /**
     * 自增方法
     */
    public static synchronized void incr() {
        //临界区代码--start
        for (int i = 0; i < 10000; i++) {
            j++;
        }
        //临界区代码--end
    }

    public static int getJ() {
        return j;
    }
}
