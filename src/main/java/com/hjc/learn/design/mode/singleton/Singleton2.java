package com.hjc.learn.design.mode.singleton;

/**
 * 2、饿汉式—静态代码块方式（线程安全）
 * 其实就是在上面 静态常量饿汉式 实现上稍微变动了一下，将类的实例化放在了静态代码块中而已。其他没区别。
 *
 * @author houjichao
 */
public class Singleton2 {

    private static Singleton2 instance;

    static {
        instance = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return instance;
    }
}
