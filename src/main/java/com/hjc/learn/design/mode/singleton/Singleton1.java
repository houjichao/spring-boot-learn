package com.hjc.learn.design.mode.singleton;

/**
 * 1、饿汉式—静态常量方式（线程安全）
 *
 * 类加载时就初始化实例，避免了多线程同步问题。天然线程安全。
 *
 * 饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。
 * 好处是编写简单，但是无法做到延迟创建对象。但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载
 *
 *
 * @author houjichao
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
