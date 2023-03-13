package com.pucc.datausability.usability.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.Rng;
import com.pucc.datausability.usability.mapper.RngMapper;
import com.pucc.datausability.usability.service.IRngService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 值域指标表 服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@Service
public class RngServiceImpl extends ServiceImpl<RngMapper, Rng> implements IRngService {

    @Override
    public List<JSONObject> selectEcharsData(String database, String table) {
        return this.baseMapper.selectEcharsData(database, table);
    }

    @Override
    public String[] selectDate() {
        return this.baseMapper.selectDate();
    }

    @Override
    public double countWithDate(String database, String table, String date) {
        return this.baseMapper.countWithDate(database, table, date);
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
