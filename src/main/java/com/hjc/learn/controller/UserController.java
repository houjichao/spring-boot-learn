package com.hjc.learn.controller;


import com.hjc.learn.entity.User;
import com.hjc.learn.model.response.CommonResponse;
import com.hjc.learn.service.PublisherService;
import com.hjc.learn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

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
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PublisherService publisherService;

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "")
    public CommonResponse save(@RequestBody User user) {
        userService.saveUser(user);
        return new CommonResponse();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{id}")
    public CommonResponse save(@PathVariable Long id) {
        log.info("删除用户id:{}", id);
        userService.removeById(id);
        return new CommonResponse();
    }

    @ApiOperation(value = "根据id查询单个用户")
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Long id) {
        //这里其实可以是查询某个用户信息后，查看是否新注册用户后是否成功发送邮件或者发送体验券，如未成功，调用补偿机制的方法
        //为了方便，直接调用实现效果即可
        //由于方法是异步的，并不影响查询
        publisherService.tolerant();
        return userService.getById(id);
    }

    @ApiOperation(value = "查询所有用户用户")
    @GetMapping(value = "")
    public Flux<List<User>> getAll() {
        return Flux.just(userService.list());
    }
}
