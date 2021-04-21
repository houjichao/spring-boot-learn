package com.hjc.learn.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 无需等待上游类型 ##### filter | map | flatMap | peek | peek |
 * 需要等待上游类型 ##### distinct | limit | sorted
 * 我们将中间操作分为两种类别。
 * 需要等待上游类型的集中操作，都是需要上游流程处理结束以后，根据结果才能做出操作的。
 *
 * https://www.jianshu.com/p/933547a77fdf
 *
 * @author houjichao
 */
public class StreamSortTest {

    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream()
                .map(this::map1)
                .map(this::map2)
                .sorted((o1, o2) -> o2 - o1)
                .map(this::map3)
                .map(this::map4)
                .sorted()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private Integer map1(Integer i) {
        int r = i * 10;
        System.out.println("线程:" + Thread.currentThread().getName() + "\t方法 map1\t" + "收到:" + i + "\t" + "输出:" + r);
        return r;
    }

    private Integer map2(Integer i) {
        int r = i * 10;
        System.out.println("线程:" + Thread.currentThread().getName() + "\t方法 map2\t" + "收到:" + i + "\t" + "输出:" + r);
        return r;
    }

    private Integer map3(Integer i) {
        int r = i * 10;
        System.out.println("线程:" + Thread.currentThread().getName() + "\t方法 map3\t" + "收到:" + i + "\t" + "输出:" + r);
        return r;
    }

    private Integer map4(Integer i) {
        int r = i * 10;
        System.out.println("线程:" + Thread.currentThread().getName() + "\t方法 map4\t" + "收到:" + i + "\t" + "输出:" + r);
        return r;
    }

}
