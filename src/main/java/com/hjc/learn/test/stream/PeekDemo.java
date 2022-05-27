package com.hjc.learn.test.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java stream peek操作
 * <p>
 * java 8 stream作为流式操作有两种操作类型，中间操作和终止操作。
 * 中间操作和终止操作
 * 一个java 8的stream是由三部分组成的。数据源，零个或一个或多个中间操作，一个或零个终止操作。
 * 中间操作是对数据的加工，注意，中间操作是lazy操作，并不会立马启动，需要等待终止操作才会执行。
 * 终止操作是stream的启动操作，只有加上终止操作，stream才会真正的开始执行。
 *
 * @author houjichao
 */
@Slf4j
public class PeekDemo {

    public static void main(String[] args) {
        test1();
        //由test1()可以证明，peek是一个中间操作

        test2();
        //我们看下peek的文档说明：peek主要被用在debug用途。

        //为什么只作为debug使用呢，再看一个例子
        test3();
        //可以看到peek操作stream中的元素并没有被转换成大写格式。

        test4();
        /*
        我们看到如果是对象的话，实际的结果会被改变。

        为什么peek和map有这样的区别呢？

        我们看下peek和map的定义：

        Stream<T> peek(Consumer<? super T> action)
        <R> Stream<R> map(Function<? super T, ? extends R> mapper);

        peek接收一个Consumer，而map接收一个Function。

        Consumer是没有返回值的，它只是对Stream中的元素进行某些操作，但是操作之后的数据并不返回到Stream中，所以Stream中的元素还是原来的元素。

        而Function是有返回值的，这意味着对于Stream的元素的所有操作都会作为新的结果返回到Stream中。

        这就是为什么peek String不会发生变化而peek Object会发送变化的原因。
         */

        /*
        3. peek VS map
            peek 操作 一般用于不想改变流中元素本身的类型或者只想元素的内部状态时；
            而 map 则用于改变流中元素本身类型，即从元素中派生出另一种类型的操作。这是他们之间的最大区别。
            那么 peek 实际中我们会用于哪些场景呢？比如对 Collection<T> 中的 T 的某些属性进行批处理的时候用 peek 操作就比较合适。
            如果我们要从 Collection<T> 中获取 T 的某个属性的集合时用 map 也就最好不过了。
         */

    }

    private static void test1() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        stream.peek(System.out::println);
    }

    private static void test2() {
        List<String> collect = Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void test3() {
        System.out.println("peek result:");
        Stream.of("one", "two", "three", "four").peek(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("map result:");
        Stream.of("one", "two", "three", "four").map(String::toUpperCase)
                .forEach(System.out::println);

    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
    }

    private static void test4() {
        List<User> userList = Stream.of(new User("a"), new User("b"), new User("c")).peek(u -> u.setName("kkk")).collect(Collectors.toList());
        log.info("{}", userList);
    }
}
