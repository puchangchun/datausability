package com.pucc.datausability.usability.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.common.vo.Result;
import com.pucc.datausability.usability.entity.AccuracyConfig;
import com.pucc.datausability.usability.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usability/dashboard")
public class DashboardController {
    @Autowired
    IDayOnDayService dayOnDayService;

    @Autowired
    IWeekOnWeekService weekOnWeekService;

    @Autowired
    IDuplicateService duplicateService;

    @Autowired
    INullIdService nullIdService;

    @Autowired
    IRngService rngService;


    @GetMapping("/warning")
    public Result<List<JSONObject>> getWarning(
            @RequestParam(value = "dt", required = false) String dt,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "database", required = false) String database
    ) {

        // data:{
        // warning:[
        //  {
        //      dt:"",
        //      database:"",
        //      table:"",
        //      col:"",
        //      error:"",
        //      level:""
        //  }
        // ]
        //  }
        //
        List<JSONObject> dayOnDayData = dayOnDayService.findWarningData(dt, level, database);
        dayOnDayData.forEach(e -> {
            e.put("error", "环比增长预警");
            e.put("error_type", 1);
        });
        List<JSONObject> weekOnWeekData = weekOnWeekService.findWarningData(dt, level, database);
        weekOnWeekData.forEach(e -> {
            e.put("error", "同比增长预警");
            e.put("error_type", 2);
        });
        List<JSONObject> duplicateData = duplicateService.findWarningData(dt, level, database);
        duplicateData.forEach(e -> {
            e.put("error", "重复数据预警");
            e.put("error_type", 3);
        });
        List<JSONObject> nullIdData = nullIdService.findWarningData(dt, level, database);
        nullIdData.forEach(e -> {
            e.put("error", "空值数据预警");
            e.put("error_type", 4);
        });
        List<JSONObject> rngData = rngService.findWarningData(dt, level, database);
        rngData.forEach(e -> {
            e.put("error", "值域范围预警");
            e.put("error_type", 5);
        });
        List<JSONObject> res = new ArrayList<>();
        res.addAll(dayOnDayData);
        res.addAll(weekOnWeekData);
        res.addAll(duplicateData);
        res.addAll(nullIdData);
        res.addAll(rngData);

        res.sort((a, b) -> b.getInteger("notification_level") - a.getInteger("notification_level"));
        return Result.success(res);

    }

    @GetMapping("/findWarning")
    public Result<JSONObject> findWarningInfo(
            @RequestParam(value = "dt", required = true) String dt,
            @RequestParam(value = "db", required = true) String db,
            @RequestParam(value = "tbl", required = true) String tbl,
            @RequestParam(value = "col", required = false) String col,
            @RequestParam(value = "error_type", required = true) Integer error_type
    ) {
        JSONObject res = new JSONObject();
        switch (error_type) {
            // 环比增长预警
            case 1:
                res = dayOnDayService.findWarningInfo(dt,db,tbl);
                res.put("error","环比增长预警");
                break;
            // 同比增长预警
            case 2:
                res = weekOnWeekService.findWarningInfo(dt,db,tbl);
                res.put("error","同比增长预警");
                break;
            // 重复数据预警
            case 3:
                res = duplicateService.findWarningInfo(dt,db,tbl,col);
                res.put("error","重复数据预警");
                break;
            // 空值数据预警
            case 4:
                res = nullIdService.findWarningInfo(dt,db,tbl,col);
                res.put("error","空值数据预警");
                break;
            // 值域范围预警
            case 5:
                res = rngService.findWarningInfo(dt,db,tbl,col);
                res.put("error","值域范围预警");
                break;
        }
        res.put("error_type",error_type);
        return Result.success(res);
    }
}
