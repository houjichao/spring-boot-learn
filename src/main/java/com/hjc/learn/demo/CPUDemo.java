package com.hjc.learn.demo;

import java.io.IOException;
import java.util.Scanner;

/**
 * 获取CPU序列号
 *
 * @author houjichao
 */
public class CPUDemo {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        //Process process = Runtime.getRuntime().exec(
        //		        new String[] { "wmic", "cpu", "get", "ProcessorId" });
        Process process = Runtime.getRuntime().exec(
                new String[]{"dmidecode", "-s","system-serial-number"});
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        System.out.println(property + ": " + serial);

        System.out.println("time:" + (System.currentTimeMillis() - start));
    }

}
