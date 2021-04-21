package com.hjc.learn.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.jianshu.com/p/1ee7b55b9eb7  stream中间步骤
 *
 * @author houjichao
 */
public class CastTimeTest {

    List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void test3() {

        long begin = System.currentTimeMillis();

        //parallelStream 并行流会很快
        //stream 还是会和for foreach一样消耗时间
        List<Integer> result = integerList.parallelStream().
                map(this::fun).
                collect(Collectors.toList());

        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("useTime : "+(end-begin));
    }

    public Integer fun(Integer integer)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return integer * integer;
    }

}
