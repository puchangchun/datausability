package com.pucc.datausability.usability.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.Rng;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 值域指标表 Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface RngMapper extends BaseMapper<Rng> {

    String[] selectDate();

    List<JSONObject> selectEcharsData(String database, String table);

    double countWithDate(String database, String table, String date);

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl, String col);
}
