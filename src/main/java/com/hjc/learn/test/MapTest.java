package com.hjc.learn.test;


import java.util.HashMap;
import java.util.Map;

/**
 * map测试
 *
 * @author houjichao
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Double> all = new HashMap<>();
        Map<String, Double> temp1 = new HashMap<>();
        temp1.put("111", 3.0);
        temp1.put("222", 4.0);
        Map<String, Double> temp2 = new HashMap<>();
        temp2.put("222", 5.0);
        temp2.put("333", 6.0);
        all.putAll(temp1);
        all.putAll(temp2);
        System.out.println(all);
        String str = "_o92dfadbddcda49c6ab208312cf6f4a55";
        System.out.println(str.substring(1));
    }

}
