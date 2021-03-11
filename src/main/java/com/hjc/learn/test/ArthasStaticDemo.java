package com.hjc.learn.test;

import com.hjc.learn.model.Movie;
import com.hjc.learn.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * arthas静态demo
 *
 * @author houjichao
 */
public class ArthasStaticDemo {

    public static Movie getMovie(String name, String director) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDirector(director);
        return movie;
    }

    public static Movie setMovie(Movie movie) {
        return movie;
    }

    public static Map mapTest(Map<String,String> map) {
        return map;
    }

    public static Person getPerson(String name, Integer age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return person;
    }

    public static Person setPerson(Person person) {
       return person;
    }

    public static Person getPerson(String name, Integer age, Integer childNum) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        Person.Child child = new Person.Child();
        child.setSex("girl");
        child.setChildAge(10);
        person.setChild(child);
        List<Person.Child> childList = new ArrayList<>();
        for (int i = 0; i < childNum; i++) {
            Person.Child temp = new Person.Child();
            temp.setSex("boy");
            temp.setChildAge(new Random().nextInt());
            childList.add(temp);
        }
        person.setChildList(childList);
        return person;
    }

}
