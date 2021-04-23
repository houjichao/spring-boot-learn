package com.hjc.learn.design.mode.factory;

/**
 * 工厂模式测试
 *
 * @author houjichao
 */
public class AbstractFactoryDemo {


    public static void main(String[] args) {
        AbstractOperationFactory operationFactory = new MyAbstractOperationFactory();
        OperationAdd operationAdd = operationFactory.createOperation(OperationAdd.class);
        operationAdd.setNumberA(3D);
        operationAdd.setNumberB(5D);
        System.out.println(operationAdd.getResult());

        OperationSub operationSub = operationFactory.createOperation(OperationSub.class);
        operationSub.setNumberA(10D);
        operationSub.setNumberB(6.5D);
        System.out.println(operationSub.getResult());
    }
}
