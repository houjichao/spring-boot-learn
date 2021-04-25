package com.hjc.learn.design.mode.singleton;

/**
 * 枚举写法
 * 使用枚举除了线程安全和防止反射强行调用构造器之外，还提供了自动序列化机制，
 * 防止反序列化的时候创建新的对象。因此，Effective Java推荐尽可能地使用枚举来实现单例。
 *
 * @author houjichao
 */

public enum Singleton8 {

    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
