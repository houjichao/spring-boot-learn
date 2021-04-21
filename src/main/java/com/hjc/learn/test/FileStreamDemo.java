package com.hjc.learn.test;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileInputStream、FileOutputStream实现文件读写功能
 * {@link com.hjc.learn.test.BufferedStreamDemo}
 *
 * @author houjichao
 */
@Slf4j
public class FileStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        try (FileInputStream input = new FileInputStream("/Users/houjichao/Work/tmp/图片/123.png");
             FileOutputStream output = new FileOutputStream("/Users/houjichao/Work/tmp/图片/456.png")) {
            byte[] bytes = new byte[1024];
            int i;
            while ((i = input.read(bytes)) != -1) {
                output.write(bytes,0,i);
            }
        } catch (IOException e) {
            log.error("复制文件发生异常",e);
        }
        log.info("常规流读写，总共耗时ms："+(System.currentTimeMillis() - begin));
    }
}
