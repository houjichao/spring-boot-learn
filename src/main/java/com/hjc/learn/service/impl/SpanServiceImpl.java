package com.hjc.learn.service.impl;

import com.hjc.learn.entity.User;
import com.hjc.learn.service.SpanService;
import org.springframework.stereotype.Service;

/**
 * 跨越测试类
 *
 * @author houjichao
 */
@Service
public class SpanServiceImpl implements SpanService {

    @Override
    public void span(User user) {
        user.setPhone("121212111");
    }

}
