package com.hjc.learn.controller;

import com.hjc.learn.model.User;
import com.hjc.learn.model.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houjichao
 */
@RestController
@RequestMapping("/v1/learn")
@Api(tags = "用户管理")
public class UserController {

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/user")
    public CommonResponse save(@RequestBody User user) {
        System.out.println("11111");
        return new CommonResponse();
    }

}
