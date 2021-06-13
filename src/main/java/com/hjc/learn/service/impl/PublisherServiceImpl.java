package com.hjc.learn.service.impl;

import com.hjc.learn.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 学习事件监听
 *
 * @author houjichao
 */
@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    @Async("lazyTraceExecutor")
    public void tolerant() {
        //模拟补偿机制
        //正常情况这里应该传入发送邮件失败或发送体验券失败的用户，进行补偿
        publisher.publishEvent(this);
        try {
            log.info("模拟补偿机制，开始睡眠");
            Thread.sleep(10000);
            log.info("模拟补偿机制，结束睡眠");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
