package com.pucc.datausability.usability.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.usability.entity.AccuracyConfig;
import com.pucc.datausability.usability.entity.DetectRepairConfig;
import com.pucc.datausability.usability.service.IDetectRepairConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pucc
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/usability/repair")
public class DetectRepairController {
    @Autowired
    IDetectRepairConfigService detectRepairService;

    @GetMapping("/databaseOps")
    public Result<List<Map<String, String>>> getDatabaseOps() {
        return Result.success(
                detectRepairService.list().stream().map(DetectRepairConfig::getDb).distinct().map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", e);
                    map.put("label", e);
                    return map;
                }).collect(Collectors.toList())
        );
    }

    @GetMapping("/tableOps")
    public Result<List<Map<String, String>>> getTableOps(
            @RequestParam(value = "databaseOpsValue", required = true) String db
    ) {
        return Result.success(
                detectRepairService.list().stream().filter(e -> e.getDb().equals(db)).distinct().map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", e.getTbl());
                    map.put("label", e.getTbl());
                    return map;
                }).distinct().collect(Collectors.toList())
        );
    }

    @GetMapping("/resTableOps")
    public Result<List<Map<String, String>>> getResTableOps(
            @RequestParam(value = "databaseOpsValue", required = true) String db,
            @RequestParam(value = "tableOpsValue", required = true) String tbl
    ) {
        return Result.success(
                detectRepairService.list().stream().filter(e -> e.getDb().equals(db) && e.getTbl().equals(tbl)).distinct().map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", e.getRes());
                    map.put("label", e.getRes());
                    return map;
                }).distinct().collect(Collectors.toList())
        );
    }

    @GetMapping("/findData")
    public Result<List<JSONObject>> findData(
            @RequestParam(value = "resTableValue", required = true) String tableName
    ) {
        List<JSONObject> res = detectRepairService.findData(tableName);
        return Result.success(res);
    }
}
