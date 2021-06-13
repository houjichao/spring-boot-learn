package com.hjc.learn.model;

import com.hjc.learn.entity.User;
import com.hjc.learn.enums.UserOperate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author houjichao
 */
@Getter
@Setter
@ToString
public class UserActionEvent extends ApplicationEvent {

    /**
     * 操作是否成功
     */
    private Boolean success;

    /**
     * 操作类型
     */
    private UserOperate operate;

    /**
     * 数据对象
     */
    private User user;

    public UserActionEvent(Object source) {
        super(source);

    }
}
