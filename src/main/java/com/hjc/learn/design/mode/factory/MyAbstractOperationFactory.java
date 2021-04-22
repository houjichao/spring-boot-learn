package com.hjc.learn.design.mode.factory;

/**
 * 具体工厂集成抽象工厂，通过反射来进行运算
 *
 * @author houjichao
 */
public class MyAbstractOperationFactory extends AbstractOperationFactory {
    @Override
    public <T extends Operation> T createOperation(Class<T> clz) {
        Operation operation = null;
        String classname = clz.getName();
        try{
            operation = (Operation)Class.forName(classname).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return (T) operation;
    }
}
