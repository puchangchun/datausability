package com.pucc.datausability.usability.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.NullId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 空值指标表 Mapper 接口
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface NullIdMapper extends BaseMapper<NullId> {

    List<String> selectColumns(String database, String table);

    List<Integer> selectCountByCol(String database, String table, String col);

    List<String> selectDate();

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl, String col);
}
