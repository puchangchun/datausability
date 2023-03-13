package com.pucc.datausability.usability.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.ConsistencyCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface ConsistencyCheckMapper extends BaseMapper<ConsistencyCheck> {

    List<JSONObject> selectAllData(String database, String table);
}
