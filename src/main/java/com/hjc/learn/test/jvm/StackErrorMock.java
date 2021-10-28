package com.hjc.learn.test.jvm;
 
/**
 * 栈溢出测试源码
 *
 * @author houjichao
 */
public class StackErrorMock {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}