package com.pucc.datausability.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.sys.entity.User;
import com.pucc.datausability.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.ObjectStreamClass;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result<List<User>> getAllUsers(){
        return Result.success(userService.list());
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data = userService.login(user);
        if (Objects.isNull(data)){
            return Result.fail();
        }
        return Result.success(data);
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息
        Map<String, Object> data = userService.getUserInfo(token);
        if (Objects.isNull(data)){
            return Result.fail(20003,"无效登录");
        }
        return Result.success(data);
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success();
    }

    @GetMapping("/find")
    public Result<Map<String, Object>>
    findUsers(
            @RequestParam(value = "username" , required = false) String username,
            @RequestParam(value = "phone" , required = false) String phone,
            @RequestParam(value = "pageNo" , required = false) Long pageNo,
            @RequestParam(value = "pageSize" , required = false) Long pageSize
            ){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        Page<User> usersPage = new Page<>(pageNo,pageSize);

        userService.page(usersPage,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",usersPage.getTotal());
        data.put("rows",usersPage.getRecords());

        return Result.success(data);
    }

    // @RequestBody 把请求体的json转化为java对象
    @PostMapping("/add")
    public Result<?> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return Result.success("新增用户成功");
    }

    @PutMapping("/update")
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateById(user);
        return Result.success("修改用户成功");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        if (Objects.isNull(user)){
            return Result.fail("没有找到用户");
        }
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Long id){
        userService.removeById(id);
        return Result.success("删除用户成功");
    }
}
