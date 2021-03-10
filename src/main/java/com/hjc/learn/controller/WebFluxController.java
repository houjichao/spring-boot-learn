package com.hjc.learn.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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
        //通过浏览器地址栏访问，对于客户端来说感觉是一样的，都是在等待了大约 5 秒后才获得了响应。但查看控制台，其输出内容是不同的。
        //即耗时操作没有阻塞了处理器的执行。这样，处理器就可以处理更多的用户请求了。虽然对于每一个客户端来说好像并没有增加用户体验，但对于处理器来说，其吞吐量却大大提高了。
        return mono;
    }

    @GetMapping("/flux")
    public Flux<String> fluxHandle(){
        //Flux表示包含0-n个元素的异步序列
        return Flux.just("beijing", "shanghai", "gunagzhou", "shenzhen");
    }

    /**
     * 数组转 Flux
     * @param cities
     * @return
     */
    @RequestMapping("/array")
    public Flux<String> fluxHandle(String[] cities){
        //Flux表示包含0-n个元素的异步序列
        return Flux.fromArray(cities);
    }

    /**
     * 集合转 Flux
     * @param cities
     * @return
     */
    @RequestMapping("/list")
    public Flux<String> fluxHandle(List<String> cities){
        //将List转为Stream,再将Stream转为Flux
        return Flux.fromStream(cities.stream());
    }


    /**
     * Flux  底层不会阻塞处理器执行
     * @param cities
     * @return
     */
    @GetMapping("/time")
    public Flux<String> timeHandle(@RequestParam List<String> cities){
        log.info("flux-start");
        //将Flux的每个元素映射为一个doThing耗时操作
        Flux<String> flux = Flux.fromStream(cities.stream().map(i -> doThing("elem-" + i)));
        log.info("flux-end");
        //2021-03-08 17:32:57.475  INFO 80335 --- [io-9100-exec-10] c.h.learn.controller.WebFluxController   : flux-start
        //2021-03-08 17:32:57.480  INFO 80335 --- [io-9100-exec-10] c.h.learn.controller.WebFluxController   : flux-end
        return flux;
    }




    /**
     * 定义耗时操作
     *
     * @param msg
     * @return
     */
    private String doThing(String msg) {
        try {
            long time = System.currentTimeMillis();
            for (int i = 0; 1_000_0 > i; i++) {
                Thread.sleep(1L);
                log.info(i+"-----------------------");
            }
            System.out.println(System.currentTimeMillis()-time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
