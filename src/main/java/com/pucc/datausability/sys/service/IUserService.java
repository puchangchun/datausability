package com.pucc.datausability.sys.service;

import com.pucc.datausability.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    User addUser(User user);

    User findByName(String username);
}
