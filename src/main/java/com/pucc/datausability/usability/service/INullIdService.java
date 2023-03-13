package com.pucc.datausability.usability.service;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.NullId;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 空值指标表 服务类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
public interface INullIdService extends IService<NullId> {

    List<String> selectColumns(String database, String table);

    List<Integer> selectCountByCol(String database, String table, String col);

    List<String> selectDate();

    List<JSONObject> findWarningData(String dt, String level, String database);

    JSONObject findWarningInfo(String dt, String db, String tbl, String col);
}
