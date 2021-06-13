package com.hjc.learn.service;

import com.hjc.learn.entity.User;
import com.hjc.learn.model.UserActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author houjichao
 */
@Component
@Slf4j
public class UserListener {

    /**
     * 监听新增用户事件
     * 异步操作，使用自定义线程池
     * EventListener使用表达式condition = "#event.operate.name()==‘ADD’"对监听进行了细化：监听类型为“新增”的事件。
     *
     * @param event
     */
    @Async("lazyTraceExecutor")
    @EventListener(value = UserActionEvent.class, condition = "#event.operate.name.equals('add')")
    public void saveUserApplicationEvent(UserActionEvent event) {
        try {
            User user = event.getUser();
            log.info("监听到新增用户:{}", user);
            // 自定义处理
            log.info("此处可以自定义操作：发送邮件、赠送新用户体验券等...");
        } catch (Exception e) {
            log.error("事件:{},执行异常:{}", event, e.getMessage());
            //此处可以记录状态，用于补偿机制
        }
    }
}
