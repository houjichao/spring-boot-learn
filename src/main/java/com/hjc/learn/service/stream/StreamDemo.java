package com.hjc.learn.service.stream;

import com.google.common.collect.Lists;
import com.hjc.learn.model.Movie;
import com.hjc.learn.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Java8 流
 *
 * @author houjichao
 */
public class StreamDemo {

    /**
     * 相同点：
     * 这几个函数都可以对数据流中的任何一个数据进行处理，比如打印
     * <p>
     * 不同点：
     * peek 这个函数返回值是一个stream，有一个比较重要的一点，就是这个并不会对流中的数据进行修改
     * map 这个函数返回值也是一个stream，但是这个流是一个新的流，内部的处理是对数据流可以进行修改，是生成的一个新的流
     * foreach 这个函数返回值是void，也就是说这个函数算是一个终结点的函数处理方式
     * <p>
     * 当我们只需要对元素内部处理，使用peek是比较合适的，如果我们需要返回一个自定义的Stream时候，需要使用map
     */

    public static void main(String[] args) {
        //只需要访问获取内部元素，打印
        List<String> stringList1 = Lists.newArrayList("11", "22", "33");
        stringList1.stream().peek(System.out::println).collect(Collectors.toList());

        List<String> stringList2 = Lists.newArrayList("11", "22", "33");

        //支持自定义返回值，将字符串转换为数字
        List<Integer> mapResultList = stringList2.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(mapResultList);

        //可以看到返回值还是List<String>
        List<String> peekResultList = stringList2.stream().peek(Integer::valueOf).collect(Collectors.toList());
        System.out.println(peekResultList);

        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("怦然心动");
        movie.setDirector("罗伯莱纳");
        movieList.add(movie);
        movieList = movieList.stream().peek(temp -> temp.setName("peek:" + temp.getName())).collect(Collectors.toList());
        System.out.println(movieList);


        List<Person> personList = Lists.newArrayList();

        Person person1 = new Person("1", 10);
        Person person2 = new Person("2", 2);
        Person person3 = new Person("3", 3);
        Person person4 = new Person("4", 4);

        personList.addAll(Arrays.asList(person1, person2, person3, person4));

        //第一种方式
        personList.forEach(item -> item.setName(item.getName() + "_test"));
        System.out.println(
                personList.stream().min((o1, o2) -> {
                    if (Objects.equals(o1.getAge(), o2.getAge())) {
                        return 0;
                    }
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                }).get().toString()
        );

        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

        //第二种方式
        System.out.println(
                personList.stream().peek(item -> item.setName(item.getName() + "_test")).min((o1, o2) -> {
                    if (Objects.equals(o1.getAge(), o2.getAge())) {
                        return 0;
                    }
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                }).get().toString()
        );

        /*
         结论：
        （1）：使用stream.foreach也可以更改list中的每个item的内部属性值等等，但是要进行“二次流处理”，才能得到list中最小的item（根据age筛选）
        （2）：stream.peek比stream.foreach() 可以跟直接拿到最小的item（根据age筛选）


         原因：
        （1）：stream.foreach的操作是void的，除了更改属性值还可以进行其他操作等。因此要做“二次流处理”。

                default
                void forEach (Consumer < ? super T > action){
                    Objects.requireNonNull(action);
                    for (T t : this) {
                        action.accept(t);
                    }
                }
        （1）：stream.peek的操作是返回一个新的stream的，且设计的初衷是用来debug调试的，因此使用steam.peek() 必须对流进行一次处理再产生一个新的stream。*/

    }

}
