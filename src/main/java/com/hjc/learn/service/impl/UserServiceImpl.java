package com.hjc.learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.learn.entity.User;
import com.hjc.learn.mapper.UserMapper;
import com.hjc.learn.service.UserService;
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



    @Override
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) >0;
    }
}
