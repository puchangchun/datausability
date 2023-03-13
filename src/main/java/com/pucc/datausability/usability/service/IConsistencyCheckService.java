package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.ConsistencyCheck;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface IConsistencyCheckService extends IService<ConsistencyCheck> {

    List<JSONObject> selectAllData(String database, String table);
}
