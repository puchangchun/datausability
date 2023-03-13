package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.Duplicate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 重复值指标表 服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface IDuplicateService extends IService<Duplicate> {

    List<String> selectDate();

    List<JSONObject> selectEcharsData(String database, String table);

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl, String col);
}
