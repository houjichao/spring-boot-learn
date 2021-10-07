package com.hjc.learn.test.pattern;

import cn.hutool.core.util.ReUtil;

import java.util.List;

/**
 * @author houjichao
 */
public class HutoolReUtil {

    public static void main(String[] args) {

        System.out.println("isMatchTest");
        isMatchTest();

        System.out.println("delTest");
        delTest();

        System.out.println("findAllTest");
        findAllTest();

        System.out.println("replaceTest");
        replaceTest();

        System.out.println("escapeTest");
        escapeTest();

    }

    /**
     * 判断是否匹配，第一个参数是正则表达式，第二个是匹配的内容
     */
    private static void isMatchTest() {
        //匹配qq邮箱格式
        String content1 = "2953078192@qq.com";
        String content2 = "295307819@qq.com";
        String content3 = "2a53078192@qq.com";
        String content4 = "2953078192@qq.cn";
        String reg = "[0-9]{10}@qq.com$";
        boolean matched1 = ReUtil.isMatch(reg, content1);
        boolean matched2 = ReUtil.isMatch(reg, content2);
        boolean matched3 = ReUtil.isMatch(reg, content3);
        boolean matched4 = ReUtil.isMatch(reg, content4);
        //true
        System.out.println(matched1);
        //false
        System.out.println(matched2);
        //false
        System.out.println(matched3);
        //false
        System.out.println(matched4);
    }

    private static void delTest() {
        String content = "smartPig,hello;my name is smartpig,smartPig";
        String result = ReUtil.delFirst("smart", content);
        System.out.println("删除第一个smart:");
        System.out.println(result);
        result = ReUtil.delAll("smart", content);
        System.out.println("删除所有smart:");
        System.out.println(result);
        /*结果：
         删除第一个smart:
        Pig,hello;my name is smartpig,smartPig
        删除所有smart:
        Pig,hello;my name is pig,Pig
        */
    }

    private static void findAllTest() {
        /**
         * 	得到所有的匹配内容
         */
        String content = "abjgjaaagjgaaaagj";
        //匹配所有a出现了1-4次的字符串
        List<String> results = ReUtil.findAll("a{1,4}", content, 0);
        for (String s : results) {
            System.out.println(s);
        }
    }

    private static void replaceTest() {
        String content = "hello1world2thank5you7";
        //将所有数字替换为下划线
        String result = ReUtil.replaceAll(content, "[0-9]", "_");
        System.out.println(result);

    }

    /**
     * 自动转义特殊字符
     */
    private static void escapeTest() {
        String content = "hello$smart[";
        String result = ReUtil.escape(content);
        System.out.println(result);
    }
}