package com.hjc.learn.test.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * ImmutableMap是一个不可变的Map对象，其构造器如下所示，用于创建不可变Map实例
 * Guava提供了每个java.util的不可变版本。使用 ImmutableMap 映射 。
 * 每当我们尝试修改它时，它都会抛出 UnsupportedOperationException。
 *
 * @author houjichao
 */
public class ImmutableMapDemo {

    public static void main(String[] args) {

        //可以链式编程的Map
        ImmutableMap<String, String> immutableMap = ImmutableMap.<String, String>builder().put("page", "page/page/index")
                .put("templateId", "")
                .put("templateName", "")
                .put("formId", "")
                .build();

        System.out.println(JSONObject.toJSONString(immutableMap));

        //3.不可变的Map
        //为什么需要不可变的集合呢？
        //
        //（1）保证线程安全：在并发程序中，使用Immutable既保证线程安全性，也大大增强了并发时的效率（跟并发锁方式相比）。尤其当一个对象是值对象时，更应该考虑采用Immutable方式；
        //（2）被不可信的类库使用时会很安全；
        //（3）如果一个对象不需要支持修改操作(mutation)，将会节省空间和时间的开销；经过分析，所有不可变的集合实现都比可变集合更加有效地利用内存；
        //（4）可以当作一个常量来对待，并且这个对象在以后也不会被改变。
        //将一个对象复制一份成immutable的，是一个防御性编程技术。
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("2", 2);
        hashMap.put("3", 3);
        hashMap.put("4", 4);
        ImmutableMap<String, Integer> copyOfMap = ImmutableMap.copyOf(hashMap);
        System.out.println(JSONObject.toJSONString(copyOfMap));
        copyOfMap.putIfAbsent("3", 4);
        System.out.println(JSONObject.toJSONString(copyOfMap));
        //Exception in thread "main" java.lang.UnsupportedOperationException
        //copyOfMap.put("3",4);
        System.out.println(JSONObject.toJSONString(copyOfMap));

    }
}
