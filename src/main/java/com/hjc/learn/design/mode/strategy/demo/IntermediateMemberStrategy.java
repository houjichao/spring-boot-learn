package com.hjc.learn.design.mode.strategy.demo;

/**
 * 中级会员折扣类
 *
 * @author houjichao
 */
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }

}