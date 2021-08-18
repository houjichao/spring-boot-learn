package com.hjc.learn.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author houjichao
 */
public class SnacksDemo {

    public static void main(String[] args) {
        System.out.print("输入:");
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        Integer money = null;
        Integer kind = null;
        List<Snacks> snacksList = Lists.newArrayList();
        while (true) {
            String text = scanner.nextLine().trim();
            if (StringUtils.isBlank(text)) {
                break;
            }
            count++;
            //校验第一行数据
            List<String> data = Splitter.on(" ").splitToList(text);
            if (count == 1) {
                try {
                    int x = Integer.parseInt(data.get(0));
                    if (x > 1000 || x < 0) {
                        throw new RuntimeException("可用钱总额错误");
                    }
                    money = x;
                    int n = Integer.parseInt(data.get(1));
                    if (n <= 0 || n > 100) {
                        throw new RuntimeException("零食种类错误");
                    }
                    kind = n;
                } catch (NumberFormatException e) {
                    throw new RuntimeException("数据错误");
                }
            }
            if (money == null || kind == null) {
                throw new RuntimeException("数据错误");
            }
            if (count >= 2) {
                try {
                    int price = Integer.parseInt(data.get(0));
                    if (price >= 100 || price <= 0) {
                        throw new RuntimeException("零食价格错误");
                    }
                    int num = Integer.parseInt(data.get(1));
                    if (num < 0 || num > 10000) {
                        throw new RuntimeException("零食数量错误");

                    }
                    int love = Integer.parseInt(data.get(2));
                    if (love < 0 || love > 10000) {
                        throw new RuntimeException("喜爱度错误");
                    }
                    Snacks snacks = new Snacks();
                    snacks.setPrice(price);
                    snacks.setNum(num);
                    snacks.setLove(love);
                    snacksList.add(snacks);
                }catch (NumberFormatException e) {
                    throw new RuntimeException("数据错误");
                }
            }

            if (count == 1 + kind) {
                if (kind != snacksList.size()) {
                    throw new RuntimeException("数据错误");
                }
            }
        }

        if (!CollectionUtils.isEmpty(snacksList)) {
            List<Snacks> newSnacks = new ArrayList<>();
            for (Snacks snacks : snacksList) {
                if (snacks.getPrice() <= money) {
                    newSnacks.add(snacks);
                }
            }
            List<Integer> price = newSnacks.stream().map(Snacks::getPrice).collect(Collectors.toList());
            List<Integer> love = newSnacks.stream().map(Snacks::getLove).collect(Collectors.toList());
            //这里调用找花钱最少，喜爱度最高的零食
            //findGoodSnacks();

            //找到之后，用money - price即可，记得校验库存
        }
    }

    public static int findGoodSnacks(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int pointG = 0;
        int pointS = 0;

        while (pointG < g.length && pointS < s.length) {
            if (g[pointG] <= s[pointS]) {
                pointG++;
                pointS++;
            }
            else {
                pointS++;
            }
        }

        return pointG;
    }

    @Data
    private static class Snacks{
        private int price;
        private int num;
        private int love;
    }

}
