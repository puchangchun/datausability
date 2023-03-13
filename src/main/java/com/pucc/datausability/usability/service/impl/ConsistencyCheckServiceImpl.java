package com.pucc.datausability.usability.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.ConsistencyCheck;
import com.pucc.datausability.usability.mapper.ConsistencyCheckMapper;
import com.pucc.datausability.usability.service.IConsistencyCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@Service
public class ConsistencyCheckServiceImpl extends ServiceImpl<ConsistencyCheckMapper, ConsistencyCheck> implements IConsistencyCheckService {

    @Override
    public List<JSONObject> selectAllData(String database, String table) {
        List<JSONObject> data = this.baseMapper.selectAllData(database,table);
        return data;
    }
}
