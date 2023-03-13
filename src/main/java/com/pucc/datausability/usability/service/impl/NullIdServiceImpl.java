package com.pucc.datausability.usability.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.NullId;
import com.pucc.datausability.usability.mapper.NullIdMapper;
import com.pucc.datausability.usability.service.INullIdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 空值指标表 服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@Service
public class NullIdServiceImpl extends ServiceImpl<NullIdMapper, NullId> implements INullIdService {

    @Override
    public List<String> selectColumns(String database, String table) {
        return this.baseMapper.selectColumns(database, table);
    }

    @Override
    public List<Integer> selectCountByCol(String database, String table, String col) {
        return this.baseMapper.selectCountByCol(database, table, col);
    }

    @Override
    public List<String> selectDate() {
        return this.baseMapper.selectDate();
    }

    @Override
    public List<JSONObject> findWarningData(String dt, String level, String database) {
        if (dt != null && dt.trim().equals("")) {
            dt = null;
        }
        if (level != null && level.trim().equals("")) {
            level = null;
        }
        if (database != null && database.trim().equals("")) {
            database = null;
        }
        return this.baseMapper.findWarningData(dt, level, database);
    }

    @Override
    public JSONObject findWarningInfo(String dt, String db, String tbl, String col) {
        return this.baseMapper.findWarningInfo(dt, db, tbl, col);
    }
}
