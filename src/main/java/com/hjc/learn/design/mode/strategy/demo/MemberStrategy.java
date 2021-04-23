package com.hjc.learn.design.mode.strategy.demo;

/**
 * 抽象折扣类
 *
 * @author houjichao
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param booksPrice 图书的原价
     * @return 计算出打折后的价格
     */
    double calcPrice(double booksPrice);
}