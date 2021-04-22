package com.hjc.learn.design.mode.factory;

/**
 * 乘法操作
 *
 * @author houjichao
 */
public class OperationMul extends Operation {

    @Override
    public double getResult() {
        double result;
        result = super.getNumberA() * super.getNumberB();
        return result;
    }

}
