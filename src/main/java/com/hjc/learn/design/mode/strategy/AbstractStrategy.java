package com.hjc.learn.design.mode.strategy;

/**
 * 抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
 *
 * @author houjichao
 */
public interface AbstractStrategy {
    /**
     * 策略方法
     */
    void strategyInterface();
}