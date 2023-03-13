package com.pucc.datausability.usability.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.WeekOnWeek;
import com.pucc.datausability.usability.mapper.WeekOnWeekMapper;
import com.pucc.datausability.usability.service.IWeekOnWeekService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 同比增长指标表 服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@Service
public class WeekOnWeekServiceImpl extends ServiceImpl<WeekOnWeekMapper, WeekOnWeek> implements IWeekOnWeekService {

    @Override
    public List<List<Object>> findData(String database, String table) {
        List<JSONObject> data = this.baseMapper.findData(database, table);
        return data.stream().map(e -> {
            List<Object> objects = new ArrayList<>();
            objects.add(e.get("dt"));
            objects.add(e.getDouble("value"));
            return objects;
        }).sorted((pre, next) -> -1).collect(Collectors.toList());
    }

    @Override
    public List<JSONObject> findWarningData(String dt, String level, String database) {
        if (dt != null && dt.trim().equals("")){
            dt = null;
        }
        if (level != null && level.trim().equals("")){
            level = null;
        }
        if (database != null && database.trim().equals("")){
            database = null;
        }
        return this.baseMapper.findWarningData(dt,level,database);
    }

    @Override
    public JSONObject findWarningInfo(String dt, String db, String tbl) {
        return this.baseMapper.findWarningInfo(dt, db, tbl);
    }
}
