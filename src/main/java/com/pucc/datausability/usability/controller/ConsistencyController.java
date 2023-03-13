package com.pucc.datausability.usability.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.usability.entity.ConsistencyConfig;
import com.pucc.datausability.usability.service.IConsistencyCheckService;
import com.pucc.datausability.usability.service.IConsistencyConfigService;
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
@RequestMapping("/usability/consistency")
public class ConsistencyController {
    @Autowired
    IConsistencyConfigService consistencyConfigService;

    @Autowired
    IConsistencyCheckService consistencyCheckService;

    @GetMapping("/databaseOps")
    public Result<List<Map<String, String>>> getDatabaseOps() {
        return Result.success(
                consistencyConfigService.list().stream().map(ConsistencyConfig::getDb).distinct().map(e -> {
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
                consistencyConfigService.list().stream().filter(e -> e.getDb().equals(db)).map(e -> {
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
        // {
        //  "data":{
        //    在这一层封装
        //    "e1Data":{
        //      "xData":[],
        //      "yData1":[],
        //      "yData2":[]
        //    },
        //    ""
        //  }
        //}

        // 1.查找表数据量变化
        List<JSONObject> data = consistencyCheckService.selectAllData(database, table);
        if (data.size() == 0){
            return Result.fail();
        }

        // 2. 封装数据量对比图
        //    "e1Data":{
        //      "xData":[],
        //      "yData1":[],
        //      "yData2":[]
        //    },
        HashMap<String, List<String>> e1DataMap = new HashMap<>();
        e1DataMap.put("xData", data.stream().map(e -> e.getString("data_date")).collect(Collectors.toList()));
        e1DataMap.put("yData1", data.stream().map(e -> e.getString("target_table_count")).collect(Collectors.toList()));
        e1DataMap.put("yData2", data.stream().map(e -> e.getString("source_table_count")).collect(Collectors.toList()));

        // 3. 封装源表数据血源
        //    "e2Data":{
        //      "data":[
        //         {
        //          name: 'Node 1',
        //          x: 800,
        //          y: 300
        //        },
        //        {
        //          name: 'Node 2',
        //          x: 300,
        //          y: 300
        //        },
        //      ],
        //      "links":[
        //       {
        //           source: 0,
        //           target: 1,
        //        },
        //      ],
        //    },
        HashMap<String, List<JSONObject>> e2DataMap = new HashMap<>();
        List<JSONObject> e2Data = new ArrayList<>();
        List<JSONObject> e2Link = new ArrayList<>();
        JSONObject targetNode = new JSONObject();
        targetNode.put("name", data.get(0).getString("database_name") + "::" + data.get(0).getString("target_table_name"));
        targetNode.put("x", 900);
        targetNode.put("y", 300);
        e2Data.add(targetNode);
        int x = 300;
        int y = 300;
        int deta = 0;
        String[] source_table_names = data.get(0).getString("source_table_name").split(",");
        for (int i = 0; i < source_table_names.length; i++) {
            JSONObject jsonObject = new JSONObject();
            JSONObject link = new JSONObject();
            link.put("source", i + 1);
            link.put("target", 0);
            jsonObject.put("name", source_table_names[i]);
            jsonObject.put("x", x);
            jsonObject.put("y", y + deta);
            e2Data.add(jsonObject);
            e2Link.add(link);
            if (i % 2 == 0) {
                if (deta >= 0) {
                    deta += 180;
                } else {
                    deta -= 180;
                }
            }
            deta = -deta;
        }

        e2DataMap.put("data", e2Data);
        e2DataMap.put("links", e2Link);

        // 4. 封装一致性对比结果
        //       e3Data: {
        //        xData: [],
        //        y1Data: [], // 表记录总数
        //        y2Data: [], // 一致性数目
        //        y3Data: [] // 源表记录数
        //        y3Data: [] // 重复数据数
        //      },
        JSONObject e3DataMap = new JSONObject();
        e3DataMap.put("xData", data.stream().map(e -> e.getString("data_date")).collect(Collectors.toList()));
        e3DataMap.put("y1Data", data.stream().map(e -> e.getString("target_table_count")).collect(Collectors.toList()));
        e3DataMap.put("y2Data", data.stream().map(e -> e.getString("consistent_data_count")).collect(Collectors.toList()));
        e3DataMap.put("y3Data", data.stream().map(e -> e.getString("source_table_count")).collect(Collectors.toList()));
        e3DataMap.put("y4Data", data.stream().map(e -> e.getString("target_duplicate_count")).collect(Collectors.toList()));

        JSONObject res = new JSONObject();
        res.put("e1Data", e1DataMap);
        res.put("e2Data", e2DataMap);
        res.put("e3Data", e3DataMap);

        return Result.success(
                res
        );
    }
}
