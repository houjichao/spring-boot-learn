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

        //为什么java里面3*0.1=0.30000000000000004，而4*0.1=0.4？
        //对于0.1来说，其本质是 1 / 10，那么若你用二进制表示它们，然后除的话，是这样的：1 ／ 1010，然而这一个是除不尽的，是无穷循环。

        //什么是往返(round-trip)？
        //任何双精度浮点数最多可以用 17 位有效十进制数字标识。这意味着如果您将浮点数转换为十进制字符串，
        //将其四舍五入（到最接近的）为 17 位，然后将其转换回浮点数，您将恢复原始浮点数。换句话说，转换将往返。

        //任何浮点数可以最多被17位十进制数字表示，这意味着如果你转换一个浮点数为十进制字符串需要保留17位数字，
        //这样可以通过这17位数字转换恢复原来的浮点数，这种转换就是round-trip


        //被打印成长字符串的浮点数比如（0.3，0.8，0.9，1.0）是因为打印出的字符串
        //(如：0.30000000000000004）是满足round-trip字符串中最短的一个


        System.out.println(1 * 0.1);
        System.out.println(2 * 0.1);
        System.out.println(3 * 0.1);
        System.out.println(4 * 0.1);
        System.out.println(5 * 0.1);
        System.out.println(6 * 0.1);
        System.out.println(7 * 0.1);
        System.out.println(8 * 0.1);
        System.out.println(9 * 0.1);
        System.out.println(10 * 0.1);



        System.out.println(3 * 0.1 == 0.3);
        System.out.println(0.3 * 1 == 0.3);
        System.out.println(4 * 0.1 == 0.4);


        double d = 0;
        for (int i = 1; i <= 10; i++) {
            d += 0.1;
            System.out.println(d);
        }
        System.out.println("---------");
        double e;
        e = 0.00000000000005684341886080801486968994140625; //2^-44
        System.out.println(e);


    }

}
