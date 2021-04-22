package com.hjc.learn.design.mode.factory;

/**
 * @author houjichao
 */
public class FactoryDemo {

    public static void main(String[] args) {

        Operation oper;
        oper = OperationFactory.createOperate("/");
        oper.setNumberA(10);
        oper.setNumberB(7);
        double result = oper.getResult();
        System.out.println(result);

    }
}
