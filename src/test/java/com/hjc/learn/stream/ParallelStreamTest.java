package com.hjc.learn.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 并行流测试
 *
 * @author houjichao
 */
public class ParallelStreamTest {

    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //注意 注意 注意 我只改动了这里 生成了一个并行流
        List<Integer> collect = list.parallelStream()
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

    /**
     * 线程:ForkJoinPool.commonPool-worker-2	方法 map1	收到:1	输出:10
     * 线程:ForkJoinPool.commonPool-worker-9	方法 map1	收到:2	输出:20
     * 线程:main	方法 map1	收到:5	输出:50
     * 线程:ForkJoinPool.commonPool-worker-9	方法 map2	收到:20	输出:200
     * 线程:ForkJoinPool.commonPool-worker-11	方法 map1	收到:3	输出:30
     * 线程:ForkJoinPool.commonPool-worker-11	方法 map2	收到:30	输出:300
     * 线程:ForkJoinPool.commonPool-worker-4	方法 map1	收到:4	输出:40
     * 线程:ForkJoinPool.commonPool-worker-4	方法 map2	收到:40	输出:400
     * 线程:ForkJoinPool.commonPool-worker-2	方法 map2	收到:10	输出:100
     * 线程:main	方法 map2	收到:50	输出:500
     *
     * 在这个位置 sorted 大家等排序完成以后继续抢工作，排序工作开始的前提是上游stream里面的内容都处理结束了。
     *
     * 线程:main	方法 map3	收到:100	输出:1000
     * 线程:main	方法 map4	收到:1000	输出:10000
     * 线程:ForkJoinPool.commonPool-worker-9	方法 map3	收到:200	输出:2000
     * 线程:ForkJoinPool.commonPool-worker-9	方法 map4	收到:2000	输出:20000
     * 线程:ForkJoinPool.commonPool-worker-11	方法 map3	收到:500	输出:5000
     * 线程:ForkJoinPool.commonPool-worker-11	方法 map4	收到:5000	输出:50000
     * 线程:ForkJoinPool.commonPool-worker-4	方法 map3	收到:300	输出:3000
     * 线程:ForkJoinPool.commonPool-worker-4	方法 map4	收到:3000	输出:30000
     * 线程:ForkJoinPool.commonPool-worker-2	方法 map3	收到:400	输出:4000
     * 线程:ForkJoinPool.commonPool-worker-2	方法 map4	收到:4000	输出:40000
     *
     * 在这个位置 sorted 大家等排序完成以后继续抢工作，排序工作开始的前提是上游stream里面的内容都处理结束了。
     * 排序结束后，limit前两个，输出到下list
     * [10000, 20000]
     */

}
