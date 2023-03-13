package com.pucc.datausability.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.sys.entity.User;
import com.pucc.datausability.sys.mapper.UserMapper;
import com.pucc.datausability.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.time.Duration;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> login(User user) {
        // 查找用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = this.baseMapper.selectOne(queryWrapper);
        // 生成token，放入redis
//        if (Objects.isNull(loginUser) || !passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {
//            return null;
//        }
        String key = "user:" + UUID.randomUUID();

        //存入Redis
        if (loginUser.getPassword() != null)
            loginUser.setPassword("");
        redisTemplate.opsForValue().set(key, loginUser, Duration.ofMinutes(30));

        HashMap<String, Object> data = new HashMap<>();
        data.put("token", key);
        return data;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 查找用户
        Object o = redisTemplate.opsForValue().get(token);
        if (Objects.isNull(o)) {
            return null;
        }
        User user = JSON.parseObject(JSON.toJSONString(o), User.class);
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", user.getUsername());
        data.put("avatar", user.getAvatar());
        List<String> rolesList = this.baseMapper.getRoleNameByUserId(user.getId());
        data.put("roles", rolesList);
        return data;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public User addUser(User user) {
        this.baseMapper.insert(user);
        return user;
    }

    @Override
    public User findByName(String username) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("username",username);
        List<User> users = this.baseMapper.selectByMap(data);
        if (users.size() != 1){
            return null;
        }
        return users.get(0);
    }
}
