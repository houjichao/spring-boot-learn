package com.hjc.learn.service.process;

import com.hjc.learn.aop.Module;
import com.hjc.learn.config.AdapterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author houjichao
 */
@Service
@Module(type = AdapterConfig.MODULE_HANDLE)
@Slf4j
public class FirstDemoProcessor {

    //@Properties
    public <T1,T2> void properties(T1 requestDTO,T2 requestBO){
        //入参转换，预处理
        log.info("FirstDemoProcessor.properties...");
    }

    //@Proceser
    public <T2> void process(T2 requestBO){
        //业务处理
        log.info("FirstDemoProcessor.process...");
    }
}