package com.hjc.learn.design.mode.singleton;

/**
 * 3、懒汉式（线程不安全）
 * 这是最基本的实现方式，第一次调用才初始化，实现了懒加载的特性。多线程场景下禁止使用，因为可能会产生多个对象，不再是单例。
 *
 * @author houjichao
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

}
