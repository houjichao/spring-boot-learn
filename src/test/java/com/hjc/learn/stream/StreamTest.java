package com.hjc.learn.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 串行流测试
 *
 * @author houjichao
 */
public class StreamTest {

    /**
     * 不管运行几次，我们都会得到相同的结果。
     * 从结果来看，main线程的处理过程是每次拿一个元素，然后顺序的进行一遍流程，做完以后进行下一个元素。最后将结果收集起来。
     */
    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream()
                .map(this::map1)
                .map(this::map2)
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

}
