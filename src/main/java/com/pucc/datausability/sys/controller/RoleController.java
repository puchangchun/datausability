package com.pucc.datausability.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.sys.entity.Role;
import com.pucc.datausability.sys.entity.User;
import com.pucc.datausability.sys.entity.UserRole;
import com.pucc.datausability.sys.service.IRoleService;
import com.pucc.datausability.sys.service.IUserRoleService;
import com.pucc.datausability.sys.service.IUserService;
import com.pucc.datausability.sys.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<JSONObject>> allRoles(
    ){
        List<JSONObject> data = roleService.findAllRoleWithUser();
        return Result.success(data);
    }

    @GetMapping("/find")
    public Result<List<JSONObject>> findRoles(
            @RequestParam(value = "username" , required = false) String username,
            @RequestParam(value = "role_name" , required = false) String rolename
    ){
        List<JSONObject> data = roleService.findRoleWithUser(username,rolename);
        return Result.success(data);
    }

    @GetMapping("/add")
    public Result<?> addRole(
            @RequestParam(value = "username" , required = false) String username,
            @RequestParam(value = "role_name" , required = false) String rolename
    ){
        User user = userService.findByName(username);
        Role role = roleService.findByName(rolename);
        if (Objects.isNull(user) || Objects.isNull(role)){
            return Result.fail("数据错误");
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getRoleId());
        userRole.setUserId(user.getId());
        userRoleService.save(userRole);
        return Result.success();
    }
}
