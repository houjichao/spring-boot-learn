package com.hjc.learn.service;

import com.hjc.learn.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hjc
 * @since 2020-08-20
 */
public interface UserService extends IService<User> {

    /**
     * 保存用户
     *
     * @param user 用户信息
     * @return 用户名
     */
    String saveUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

}
