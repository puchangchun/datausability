package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.DetectRepairConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-07
 */
public interface IDetectRepairConfigService extends IService<DetectRepairConfig> {

    List<JSONObject> findData(String tableName);
}
