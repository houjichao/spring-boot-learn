package com.hjc.learn.test;

class Father {
	//可以将此调换顺序验证加载机制
	static {
		a = 2;
	}

    public static int a = 1;

}

class Child extends Father {
    public static int b = a;
}

/**
 * @author houjichao
 */
public class ClinitTest {
    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}