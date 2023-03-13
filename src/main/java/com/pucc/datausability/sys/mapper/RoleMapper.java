package com.pucc.datausability.sys.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
public interface RoleMapper extends BaseMapper<Role> {
    public List<JSONObject> allRolesWithUser();

    List<JSONObject> findRolesWithUser(String username, String rolename);
}
