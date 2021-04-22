package com.hjc.learn.design.mode.factory;

/**
 * 减法操作
 *
 * @author houjichao
 */
public class OperationSub extends Operation {

    @Override
    public double getResult() {
        double result;
        result = super.getNumberA() - super.getNumberB();
        return result;
    }

}
