package com.hjc.learn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hjc.learn.entity.User;
import com.hjc.learn.service.BusinessService;
import com.hjc.learn.service.SpanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void spanTest() {
        User user = new User();
        user.setName("hjc");
        spanService.span(user);
        log.info("user:{}", JSONObject.toJSON(user));

    }
}
