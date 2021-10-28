package com.hjc.learn.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较 JDK 1.6 与 JDK 1.7及 JDK 1.8 的区别
 *
 * @author houjichao
 */
public class StringOomMock {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}