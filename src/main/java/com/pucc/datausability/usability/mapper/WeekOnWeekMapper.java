package com.pucc.datausability.usability.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.WeekOnWeek;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 同比增长指标表 Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface WeekOnWeekMapper extends BaseMapper<WeekOnWeek> {

    List<JSONObject> findData(String database, String table);

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl);
}
