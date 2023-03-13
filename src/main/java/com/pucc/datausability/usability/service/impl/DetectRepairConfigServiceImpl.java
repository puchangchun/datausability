package com.pucc.datausability.usability.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.usability.entity.DetectRepairConfig;
import com.pucc.datausability.usability.mapper.DetectRepairConfigMapper;
import com.pucc.datausability.usability.service.IDetectRepairConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pucc
 * @since 2023-03-07
 */
@Service
public class DetectRepairConfigServiceImpl extends ServiceImpl<DetectRepairConfigMapper, DetectRepairConfig> implements IDetectRepairConfigService {

    @Override
    public List<JSONObject> findData(String tableName) {
        return this.baseMapper.findData(tableName);
    }
}
