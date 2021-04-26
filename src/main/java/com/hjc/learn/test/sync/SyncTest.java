package com.hjc.learn.test.sync;

/**
 * synchronized 修饰普通函数
 *
 * 监视器锁（monitor）便是对象实例（this）
 *
 * @author houjichao
 */
public class SyncTest {

    private int j = 0;

    /**
     * 自增方法
     */
    public synchronized void incr() {
        //临界区代码--start
        for (int i = 0; i < 10000; i++) {
            j++;
        }
        //临界区代码--end
    }

    public int getJ() {
        return j;
    }
}
