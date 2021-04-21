package com.hjc.learn.design.mode.factory;

/**
 * https://blog.csdn.net/pnjlc/article/details/52671310?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control
 *
 * @author houjichao
 */
public abstract class Operation {

    private double numberA = 0;
    private double numberB = 0;

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    public double getNumberA() {
        return numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public double getResult() {
        double result = 0;
        return result;
    }

}
