package com.hjc.learn.design.mode.singleton;

/**
 * 5、懒汉式（线程安全，方法上加同步锁） 考虑线程安全的写法
 * 注意volatile关键字的使用，保证了各线程对singleton静态实例域修改的可见性。
 * <p>
 * 这种写法考虑了线程安全，将对singleton的null判断以及new的部分使用synchronized进行加锁。
 * 同时，对singleton对象使用volatile关键字进行限制，保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。
 * 如此即可从语义上保证这种单例模式写法是线程安全的。注意，这里说的是语义上，实际使用中还是存在小坑的，会在后文写到。
 *
 * @author houjichao
 */
public class Singleton5 {
    private static volatile Singleton5 singleton = null;

    private Singleton5() {
    }

    public static Singleton5 getSingleton() {
        synchronized (Singleton5.class) {
            if (singleton == null) {
                singleton = new Singleton5();
            }
        }
        return singleton;
    }
}
