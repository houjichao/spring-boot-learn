package com.hjc.learn.controller;


import com.hjc.learn.entity.User;
import com.hjc.learn.model.response.CommonResponse;
import com.hjc.learn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author houjichao
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController{

    @Autowired
    UserService userService;

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/user")
    public CommonResponse save(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return new CommonResponse();
    }
}
