package com.hjc.learn.design.mode.factory;

/**
 * 抽象工厂
 *
 * 简单工厂模式与工厂模式的对比
 * 简单工厂模式：如果要增加产品，就需要在工厂类添加一个Case分支，违反了开放封闭原则，对修改也开放了。
 * 工厂模式没有违反这个原则，如果要生产VIVO手机，无需修改工厂类，直接创建产品即可。
 *
 * @author houjichao
 */
public abstract class AbstractOperationFactory {

    public abstract<T extends Operation> T createOperation(Class<T> clz);

}
