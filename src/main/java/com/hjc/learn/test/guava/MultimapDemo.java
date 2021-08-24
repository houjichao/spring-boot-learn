package com.hjc.learn.test.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

/**
 * 我们希望得到键可以重复的map数据结构，这样在查询特定键时可以返回多个值，类似数据库的查询。
 * Google Guava提供了一种优雅的数据结构Multimap可以实现一个键对应到多个值的效果。
 *
 *
 * @author houjichao
 */
public class MultimapDemo {

    public static void main(String[] args) {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        // Adding some key/value
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // Getting values
        Collection<String> fruits = myMultimap.get("Fruits");
        //  [Bannana, Apple, Pear, Pear]
        System.out.println(fruits);
        // [Bannana, Apple, Pear]
        System.out.println(ImmutableSet.copyOf(fruits));
        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables);

        //想要get返回List或者Set，在定义时可以定义为ListMultimap，SetMultimap和SortedSetMultimap等等
        ListMultimap<String,String> listMultimap = ArrayListMultimap.create();
        // Returns a List, not a Collection.
        List<String> myValues = listMultimap.get("myKey");
    }


}
