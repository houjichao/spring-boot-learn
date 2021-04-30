package com.hjc.learn.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期demo
 *
 * @author houjichao
 */
public class DateDemo {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);

        Date testDate = calendar.getTime();

        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf.format(testDate));

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(5, -1);
        System.out.println(c.getTime());

        //如果时间字符串的格式和pattern的格式不一致，时间转换完就会有问题，2021-04-27就会转换为2020.12.04
        String dateString = "2021-04-27";
        String pattern = "yyyyMMdd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            System.out.println(formatter.parse(dateString));
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }

    }
}
