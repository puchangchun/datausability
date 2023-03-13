package com.pucc.datausability.sys.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
public interface IRoleService extends IService<Role> {

    List<JSONObject> findAllRoleWithUser();

    List<JSONObject> findRoleWithUser(String username, String rolename);

    Role findByName(String rolename);
}
