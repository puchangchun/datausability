package com.pucc.datausability.sys.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.sys.entity.Role;
import com.pucc.datausability.sys.entity.User;
import com.pucc.datausability.sys.mapper.RoleMapper;
import com.pucc.datausability.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-02-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<JSONObject> findAllRoleWithUser() {
        return this.baseMapper.allRolesWithUser();
    }

    @Override
    public List<JSONObject> findRoleWithUser(String username, String rolename) {
        if (username.isEmpty()){
            username=null;
        }
        if (rolename.isEmpty()){
            rolename=null;
        }
        return this.baseMapper.findRolesWithUser(username,rolename);
    }

    @Override
    public Role findByName(String rolename) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("role_name",rolename);
        List<Role> users = this.baseMapper.selectByMap(data);
        if (users.size() != 1){
            return null;
        }
        return users.get(0);
    }


}
