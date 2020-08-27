package com.hjc.learn.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author houjichao
 */
@RestController
@RequestMapping("/webflux")
@Api(tags = "Flux测试")
@Slf4j
public class WebFluxController {

    @GetMapping("/common")
    public String commonHandle(){
        log.info("common-start{}",System.currentTimeMillis());
        //执行耗时操作
        String result = doThing("common handler");
        log.info("common-end{}",System.currentTimeMillis());
        return result;
    }

    @GetMapping("/mono")
    public Mono<String> monoHandle(){
        log.info("mono-start{}",System.currentTimeMillis());
        //执行耗时操作
        Mono<String> mono = Mono.fromSupplier(() -> doThing("mono handle"));
        log.info("mono-end{}",System.currentTimeMillis());
        //Mono表示包含0或1个元素的异步序列
        return mono;
    }

    @GetMapping("/flux")
    public Flux<String> fluxHandle(){
        //Flux表示包含0-n个元素的异步序列
        return Flux.just("beijing", "shanghai", "gunagzhou", "shenzhen");
    }

    /**
     * 定义耗时操作
     *
     * @param msg
     * @return
     */
    private String doThing(String msg) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
