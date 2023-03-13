package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.WeekOnWeek;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 同比增长指标表 服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface IWeekOnWeekService extends IService<WeekOnWeek> {

    List<List<Object>> findData(String database, String table);

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl);
}
