package com.pucc.datausability.usability.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.usability.entity.AccuracyConfig;
import com.pucc.datausability.usability.entity.CompletenessConfig;
import com.pucc.datausability.usability.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/usability/accuracy")
public class AccuracyController {
    @Autowired
    IAccuracyConfigService accuracyConfigService;

    @Autowired
    IDayOnDayService dayOnDayService;

    @Autowired
    IWeekOnWeekService weekOnWeekService;

    @Autowired
    IRngService rngService;

    @GetMapping("/databaseOps")
    public Result<List<Map<String, String>>> getDatabaseOps() {
        return Result.success(
                accuracyConfigService.list().stream().map(AccuracyConfig::getDb).distinct().map(e -> {
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
                accuracyConfigService.list().stream().filter(e -> e.getDb().equals(db)).map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", e.getTbl());
                    map.put("label", e.getTbl());
                    return map;
                }).distinct().collect(Collectors.toList())
        );
    }


    @GetMapping("/findData")
    public Result<?> findData(
            @RequestParam(value = "database", required = true) String database,
            @RequestParam(value = "table", required = true) String table
    ) {
        //               data: [
        //                ['2019-10-10', 200],
        //                ['2019-10-11', 560],
        //                ['2019-10-12', 750],
        //                ['2019-10-13', 580],
        //                ['2019-10-14', 250],
        //                ['2019-10-15', 300],
        //                ['2019-10-16', 450],
        //                ['2019-10-17', 300],
        //                ['2019-10-18', 100]
        //              ]

        // 环比增长率
        List<List<Object>> e1Data = dayOnDayService.findData(database, table);
        // 同比增长率
        List<List<Object>> e2Data = weekOnWeekService.findData(database, table);
        //

        // 值域检测
        JSONObject e4Data = new JSONObject();
        List<List<Object>> e4Source = new ArrayList<>();
        List<JSONObject> e4Series = new ArrayList<>();
        List<Object> head = new ArrayList<>();
        List<JSONObject> duplicateData = rngService.selectEcharsData(database, table);
        for (String date : rngService.selectDate()) {
            double count = rngService.countWithDate(database, table, date);
            List<JSONObject> someData = duplicateData.stream()
                    // JSONObject 获取得字符串是“”xxx“” ...
                    .filter(e -> e.getString("dt").equals("\"" + date + "\""))
                    .collect(Collectors.toList());
            List<Object> row = new ArrayList<>();
            row.add(date);
            if (head.size() == 0) {
                head = new ArrayList<>();
                head.add("product");
                for (JSONObject o : someData) {
                    JSONObject jsonObject = new JSONObject();
                    head.add(o.getString("col"));
                    jsonObject.put("type", "bar");
                    e4Series.add(jsonObject);
                    row.add(o.getString("value"));
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "bar");
                e4Series.add(jsonObject);
                row.add(count);
                head.add("总数");
                e4Source.add(head);
            } else {
                for (JSONObject o : someData) {
                    row.add(o.getString("value"));
                }
                row.add(count);
            }
            e4Source.add(row);
        }
        e4Data.put("source", e4Source);
        e4Data.put("series", e4Series);


        JSONObject res = new JSONObject();
        res.put("e1Data", e1Data);
        res.put("e2Data", e2Data);
        res.put("e4Data", e4Data);

        return Result.success(res);
    }
}
