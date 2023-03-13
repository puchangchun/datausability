package com.pucc.datausability.usability.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.DetectRepairConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-03-07
 */
public interface DetectRepairConfigMapper extends BaseMapper<DetectRepairConfig> {

    List<JSONObject> findData(String tableName);
}
