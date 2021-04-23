package com.hjc.learn.design.mode.factory;

/**
 *  * 1、简单工厂模式是属于创建型模式，又叫做静态工厂方法模式。
 *  * 它的实现方式是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。
 *  *
 *  * 2、简单工厂模式由三种角色组成： 
 *  * （1）工厂角色：简单工厂模式的核心，它负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
 *  * （2）抽象产品角色：简单工厂模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。 
 *  * （3）具体产品角色：是简单工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。
 *  * ————————————————
 *  * 原文链接：https://blog.csdn.net/pnjlc/article/details/52671310
 *
 * 简单工厂模式工厂角色
 *
 * 场景
 * *工厂类负责创建的对象比较少
 * *客户只需要知道传入工厂的参数，而无需关心创建对象的逻辑
 * 优点：根据参数获得对应的实例，避免了直接实例化类，降低了耦合性。
 *
 * 缺点：可实例化的类型在编译期已被确定，如果增加新类型（除了加减乘除，增加指数等运算方式），则要修改工厂，
 * 违反了开放封闭原则。当子类过多或子类层次过多时不适合使用。
 *
 * @author houjichao
 */
public class OperationFactory {

    public static Operation createOperate(String operate) {
        Operation oper;
        switch (operate) {
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            case "+":
            default:
                oper = new OperationAdd();
        }
        return oper;
    }

}
