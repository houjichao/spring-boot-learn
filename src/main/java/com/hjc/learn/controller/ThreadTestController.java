package com.hjc.learn.controller;

import com.hjc.learn.service.ThreadPoolDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houjichao
 */
@RestController
@RequestMapping("/thread")
@Api(tags = "thread pool操作")
@Slf4j
@RefreshScope
public class ThreadTestController {

    @Autowired
    ThreadPoolDemoService threadPoolDemoService;

    @ApiOperation(value = "线程池测试")
    @GetMapping("/test")
    public void get() {
        threadPoolDemoService.threadPoolTestOne();
    }
}
