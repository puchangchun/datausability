package com.pucc.datausability;

import com.alibaba.fastjson2.JSONObject;
import com.pucc.datausability.sys.entity.User;
import com.pucc.datausability.sys.mapper.RoleMapper;
import com.pucc.datausability.sys.mapper.UserMapper;
import com.pucc.datausability.usability.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DatausabilityApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private ConsistencyCheckMapper consistencyCheckMapper;

    @Resource
    private NullIdMapper nullIdMapper;

    @Resource
    private DuplicateMapper duplicateMapper;

    @Resource
    private DayOnDayMapper dayOnDayMapper;

    @Autowired
    private WeekOnWeekMapper weekOnWeekMapper;


    @Resource
    private RngMapper rngMapper;


    @Test
    void contextLoads() {
//        nullIdMapper.selectColumns("test_database","test_table_1").forEach(System.out::println);
//        nullIdMapper.selectCountByCol("test_database","test_table_1","col1").forEach(System.out::println);
//        consistencyCheckMapper.selectAllData("database-test", "target-test").forEach(System.out::println);

//        List<JSONObject> duplicateData = duplicateMapper.selectEcharsData("test_database", "test_table_1");
//        System.out.println(duplicateMapper.selectDate().stream().findAny().get());
//        System.out.println(duplicateData.stream().findAny().get());
//
//        System.out.println(duplicateData.stream().filter(e -> {
//            String dt = e.getString("dt");
//            System.out.println(dt);
//            boolean f = dt.equals("2023-03-08");
//            System.out.println(f);
//            return f;
//        }).count());
//        dayOnDayMapper.findWarningData(null,null,null).forEach(System.out::println);
//        nullIdMapper.findWarningData(null,null,null).forEach(System.out::println);
//        rngMapper.findWarningData(null,null,null).forEach(System.out::println);
//        weekOnWeekMapper.findWarningData(null,null,null).forEach(System.out::println);
//        duplicateMapper.findWarningData(null,null,null).forEach(System.out::println);

        System.out.println(rngMapper.findWarningInfo("2023-03-06",  "database_test", "test_table",  "col3"));


    }

}
