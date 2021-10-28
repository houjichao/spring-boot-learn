package com.hjc.learn.test.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态生成类来模拟 “PermGen space”的内存溢出
 *
 * @author houjichao
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.hjc.learn.test.guava.ImmutableMapDemo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}