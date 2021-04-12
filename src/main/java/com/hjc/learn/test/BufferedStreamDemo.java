package com.hjc.learn.test;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用FileInputStream、FileOutputStream实现文件读写功能，是没有什么问题的。
 * 但是呢，可以使用缓冲流BufferedReader、BufferedWriter、BufferedInputStream、BufferedOutputStream等，减少IO次数，提高读写效率。
 * <p>
 * 如果是不带缓冲的流，读取到一个字节或者字符的，就会直接输出数据了。而带缓冲的流，
 * 读取到一个字节或者字符时，先不输出，而是等达到缓冲区的最大容量，才一次性输出。
 *
 * @author houjichao
 */
@Slf4j
public class BufferedStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("/Users/houjichao/Work/tmp/图片/123.png"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/houjichao/Work/tmp/图片/789.png"))) {
            byte[] bytes = new byte[1024];
            int i;
            while ((i = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, i);
            }
        } catch (IOException e) {
            log.error("复制文件发生异常", e);
        }
        log.info("总共耗时ms" + (System.currentTimeMillis() - begin));
    }

}
