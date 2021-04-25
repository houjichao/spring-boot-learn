package com.hjc.learn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos集成
 *
 * @author houjichao
 */
@RestController
@RequestMapping("/nacos")
@Api(tags = "nacos操作相关")
@Slf4j
@RefreshScope
public class NacosController {

    @Value("${nacosTest:123}")
    private String nacosConfigTest;

    /**
     * dataId是spring.application.nam,groupId是默认
     * curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=hjc-learn&group=DEFAULT_GROUP&content=nacosTest=456"
     *
     * @return nacos配置
     */
    @ApiOperation(value = "获取nacos配置")
    @GetMapping("/get")
    public String get() {
        return nacosConfigTest;
    }

}
