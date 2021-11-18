package com.hjc.learn.service.impl;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.learn.entity.User;
import com.hjc.learn.enums.UserOperate;
import com.hjc.learn.mapper.UserMapper;
import com.hjc.learn.model.UserActionEvent;
import com.hjc.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hjc
 * @since 2020-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Value("app.hjc.secretKey")
    private String secretKey;

    @Override
    public String saveUser(User user) {
        Long userId = user.getId();
        user.setPhone(AES.encrypt(user.getPhone(), secretKey));
        this.saveOrUpdate(user);
        if (userId == null) {
            //如果是新增用户才发布通知
            UserActionEvent userActionEvent = new UserActionEvent(this);
            userActionEvent.setOperate(UserOperate.ADD);
            userActionEvent.setSuccess(true);
            userActionEvent.setUser(user);
            publisher.publishEvent(userActionEvent);
        }
        return user.getName();
    }

    @Override
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
