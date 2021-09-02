package com.hjc.learn.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author houjichao
 */
@Slf4j
public abstract class CommonMethodService {

    //计算逻辑
    private static Random random = new Random();

    protected int getMoreData() {
        log.info("getMoreData thread name:{}", Thread.currentThread().getName());
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to compute, passed:" + System.currentTimeMillis());
        return random.nextInt(1000);
    }

}
