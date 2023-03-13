package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.Rng;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 值域指标表 服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface IRngService extends IService<Rng> {

    List<JSONObject> selectEcharsData(String database, String table);

    String[] selectDate();

    double countWithDate(String database, String table, String date);

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl, String col);
}
