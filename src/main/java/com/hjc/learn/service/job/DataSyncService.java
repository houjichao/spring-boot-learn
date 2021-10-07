package com.hjc.learn.service.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author houjichao
 */
@EnableScheduling
@Service
@Slf4j
public class DataSyncService {

    @Scheduled(cron = "0 0 6-7 * * ?")
    protected void dataSync() {
        log.info("do data sync");
    }

}
