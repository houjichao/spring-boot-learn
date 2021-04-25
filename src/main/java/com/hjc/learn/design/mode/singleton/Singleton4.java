package com.hjc.learn.design.mode.singleton;

/**
 * 4、懒汉式（线程安全，方法上加同步锁）
 * 和上面 懒汉式（线程不安全）实现上唯一不同是：获取实例的getInstance()方法上加了同步锁。保证了多线程场景下的单例。
 * 但是效率会有所折损，不过还好。
 *
 * @author houjichao
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }

    public static synchronized  Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }

}
