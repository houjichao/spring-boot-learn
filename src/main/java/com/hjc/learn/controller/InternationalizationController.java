package com.hjc.learn.controller;

import com.hjc.learn.model.Movie;
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
@RequestMapping("/v1/movie")
@Api(tags = "影片管理")
public class InternationalizationController {

    @ApiOperation(value = "新增影片")
    @PostMapping(value = "/save")
    public CommonResponse save(@RequestBody Movie movie) {
        System.out.println("11111");
        return new CommonResponse();
    }

}
