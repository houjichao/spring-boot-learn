package com.hjc.learn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hjc.learn.entity.User;
import com.hjc.learn.service.BusinessService;
import com.hjc.learn.service.SpanService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 业务测试
 *
 * @author houjichao
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    SpanService spanService;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void spanTest() {
        User user = new User();
        user.setName("hjc");
        spanService.span(user);
        log.info("user:{}", JSONObject.toJSON(user));

    }

    @Override
    public void redissonLock() {

        RLock disLock = redissonClient.getLock("DISLOCK");
        boolean isLock;
        try {
            //尝试获取分布式锁
            isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            log.error("redissonLock exception:{}", e.getMessage());
        } finally {
            // 无论如何, 最后都要解锁
            disLock.unlock();
        }

    }
}
