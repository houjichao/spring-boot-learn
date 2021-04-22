package com.hjc.learn.design.mode.factory;

/**
 * 加法操作
 *
 * @author houjichao
 */
public class OperationAdd extends Operation{

    @Override
    public double getResult() {
        double result;
        result = super.getNumberA() + super.getNumberB();
        return result;
    }
}
