package com.hjc.learn.design.mode.factory;

/**
 * 除法操作
 *
 * @author houjichao
 */
public class OperationDiv extends Operation{

    @Override
    public double getResult() {
        double result;
        if (super.getNumberB() == 0) {
            throw new ArithmeticException("除数不能为0.");
        }
        result = super.getNumberA() / super.getNumberB();
        return result;

    }

}
