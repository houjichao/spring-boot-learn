package com.hjc.learn.controller;

import com.hjc.learn.service.IfElseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * if-else改写思路
 *
 * @author houjichao
 */
@RestController
@RequestMapping("/if-else")
@Api(tags = "if else改写")
@Slf4j
@RefreshScope
public class IfElseController {

    @Autowired
    IfElseService ifElseService;

    @ApiOperation(value = "枚举改写if-else")
    @GetMapping("/enum")
    public void get() {
        ifElseService.ifElseByEnum();
    }

}
