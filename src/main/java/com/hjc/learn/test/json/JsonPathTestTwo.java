package com.hjc.learn.test.json;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

/**
 * @author houjichao
 */
public class JsonPathTestTwo {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("file/store.json");
        String path = classPathResource.getPath();
        String fileData = FileUtil.readUtf8String(path);
        DocumentContext documentContext = JsonPath.parse(fileData);

        /*
         * 取出book里面的start-end个元素,前闭后开
         */
        List<Object> arraySplit = documentContext.read("$..book[0:2]");
        for (Object p : arraySplit) {
            System.out.println("book:----" + p.toString());
        }

        /*
         * 取出book里面的从第二个开始，一直到结束
         */
        List<Object> arraySplit1 = documentContext.read("$..book[2:]");
        for (Object p : arraySplit1) {
            System.out.println("book:----" + p.toString());
        }


        /*
         * 取出book里面的倒数两个元素
         */
        List<Object> arraySplit2 = documentContext.read("$..book[-2:]");
        for (Object p : arraySplit2) {
            System.out.println("book:----" + p.toString());
        }
    }
}
