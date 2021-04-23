package com.hjc.learn.design.mode.strategy;

/**
 * 持有一个Strategy的引用
 *
 * @author houjichao
 */
public class StrategyContext {

    /**
     * 持有一个具体策略的对象
     */
    private AbstractStrategy strategy;

    /**
     * 构造函数，传入一个具体策略对象
     *
     * @param strategy 具体策略对象
     */
    public StrategyContext(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public void contextInterface() {
        strategy.strategyInterface();
    }

}