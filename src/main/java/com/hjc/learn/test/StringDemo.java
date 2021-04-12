package com.hjc.learn.test;

/**
 * 字符串常量池
 *
 * @author houjichao
 */
public class StringDemo {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "a";
        String s3 = new String("a");
        System.out.println(s1 == s2);// true
        System.out.println(s1 == s3);// false
        System.out.println(s2 == s3);// false
    }
}
