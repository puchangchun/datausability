package com.pucc.datausability.usability.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.usability.entity.CompletenessConfig;
import com.pucc.datausability.usability.entity.ConsistencyConfig;
import com.pucc.datausability.usability.service.ICompletenessConfigService;
import com.pucc.datausability.usability.service.IDuplicateService;
import com.pucc.datausability.usability.service.INullIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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
@RequestMapping("/usability/completeness")
public class CompletenessController {

    @Autowired
    ICompletenessConfigService completenessConfigService;

    @Autowired
    INullIdService nullIdService;

    @Autowired
    IDuplicateService duplicateService;

    @GetMapping("/databaseOps")
    public Result<List<Map<String, String>>> getDatabaseOps() {
        return Result.success(
                completenessConfigService.list().stream().map(CompletenessConfig::getDb).distinct().map(e -> {
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
                completenessConfigService.list().stream().filter(e -> e.getDb().equals(db)).map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", e.getTbl());
                    map.put("label", e.getTbl());
                    return map;
                }).distinct().collect(Collectors.toList())
        );
    }

    @GetMapping("/findData")
    public Result<?> getTableOps(
            @RequestParam(value = "database", required = true) String database,
            @RequestParam(value = "table", required = true) String table
    ) {
        JSONObject res = new JSONObject();

        // [
        //     data:[],
        //     series: [
        //     {
        //       name: 'Direct',
        //       type: 'bar',
        //       stack: 'total',
        //       label: {
        //         show: true
        //       },
        //       emphasis: {
        //         focus: 'series'
        //       },
        //       data: this.e1.y1Data
        //     },
        //     ]

        JSONObject serieConfig = new JSONObject();

        serieConfig.put("type", "bar");
        serieConfig.put("stack", "total");
        JSONObject label = new JSONObject();
        label.put("show", true);
        serieConfig.put("label", label);
        JSONObject emphasis = new JSONObject();
        emphasis.put("focus", "series");
        serieConfig.put("emphasis", emphasis);

        JSONObject e1Data = new JSONObject();
        List<JSONObject> series = new ArrayList<>();

        // 封装数据
        // 1. db,tbl,col,data,[1,2,3]
        // columns
        // 1 先查询所有的列名
        List<String> cols = nullIdService.selectColumns(database, table);
        // 2 根据db,tbl,col 查找最近31天的数据
        cols.forEach(col -> {
            JSONObject element = new JSONObject(serieConfig);
            element.put("name", col);
            element.put("data", nullIdService.selectCountByCol(database, table, col));
            series.add(element);
        });

        e1Data.put("series", series);
        e1Data.put("xData", nullIdService.selectDate());

        // 处理重复数据检测
        //             source: [
        //              ['product', 'col1', 'col2', 'col3'],
        //              ['2023-03-04', 43.3, 85.8, 93.7],
        //              ['2023-03-04', 83.1, 73.4, 55.1],
        //              ['2023-03-04', 86.4, 65.2, 82.5],
        //              ['2023-03-04', 72.4, 53.9, 39.1]
        //            ],
        //             series: [{type: 'bar'}, {type: 'bar'}, {type: 'bar'}],
        // select
        //
        // from
        // where db = #{database} and tbl = #{table}
        // group by dt,
        // order by dt desc,col desc
        // limit 31

        JSONObject e2Data = new JSONObject();
        List<List<Object>> e2Source = new ArrayList<>();
        List<JSONObject> e2Series = new ArrayList<>();
        List<Object> head = new ArrayList<>();
        List<JSONObject> duplicateData = duplicateService.selectEcharsData(database, table);
        for (String date : duplicateService.selectDate()) {
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
                    head.add(o.getString("col"));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "bar");
                    e2Series.add(jsonObject);
                    row.add(o.getString("value"));
                }
                e2Source.add(head);
            } else {
                for (JSONObject o : someData) {
                    row.add(o.getString("value"));
                }
            }
            e2Source.add(row);
        }
//        List<List<Object>> collect = e2Source.stream().sorted((a, b) -> -1).collect(Collectors.toList());
//        collect.add(0,head);
        e2Data.put("source", e2Source);
        e2Data.put("series", e2Series);

        res.put("e1Data", e1Data);
        res.put("e2Data", e2Data);


        return Result.success(res);
    }
}

