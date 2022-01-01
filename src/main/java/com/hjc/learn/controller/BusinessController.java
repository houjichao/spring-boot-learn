package com.hjc.learn.controller;

import com.hjc.learn.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author houjichao
 */
@RestController
@RequestMapping("/test")
@Api(tags = "learn测试类")
@Slf4j
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @ApiOperation(value = "span test")
    @GetMapping("/span")
    public void span() {
        businessService.spanTest();
    }


    @ApiOperation(value = "redisson test")
    @GetMapping("/redisson")
    public Boolean redisson() {
        businessService.redissonLock();
        return true;
    }

}
