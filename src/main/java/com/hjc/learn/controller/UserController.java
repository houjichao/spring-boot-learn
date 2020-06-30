package com.hjc.learn.controller;

import com.hjc.learn.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houjichao
 */
@RestController
@RequestMapping("/v1/learn")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void save(@RequestBody User user) {

    }

}
