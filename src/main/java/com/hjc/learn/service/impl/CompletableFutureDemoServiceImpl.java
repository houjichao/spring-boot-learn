package com.hjc.learn.service.impl;

import com.hjc.learn.service.CommonMethodService;
import com.hjc.learn.service.CompletableFutureDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * CompletableFuture
 *
 * @author houjichao
 */
@Service
@Slf4j
public class CompletableFutureDemoServiceImpl extends CommonMethodService implements CompletableFutureDemoService {
    @Resource(name = "myExecutor")
    Executor executor;

    List<String> url = new ArrayList<>();

    @Override
    public void thenRunDemo() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::getMoreData);

        /*
         * 不消费CompletableFuture对象的结果，执行一个新任务。
         */
        CompletableFuture<Void> future2 = future.thenRunAsync(() -> {
            log.info("future执行完成了");
        });

        log.info("执行到最后一段代码了，future result：" + future.get());
        log.info("执行到最后一段代码了，future2 result：" + future2.get());
    }


}
