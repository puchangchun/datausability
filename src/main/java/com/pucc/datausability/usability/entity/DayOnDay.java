package com.pucc.datausability.usability.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 环比增长指标表
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@TableName("day_on_day")
public class DayOnDay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private LocalDate dt;

    /**
     * 数据库名
     */
    private String db;

    /**
     * 表名
     */
    private String tbl;

    /**
     * 环比增长百分比
     */
    private Double value;

    /**
     * 增长上限
     */
    private Double valueMin;

    /**
     * 增长上限
     */
    private Double valueMax;

    /**
     * 警告级别
     */
    private Integer notificationLevel;

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    public Double getValueMin() {
        return valueMin;
    }

    public void setValueMin(Double valueMin) {
        this.valueMin = valueMin;
    }
    public Double getValueMax() {
        return valueMax;
    }

    public void setValueMax(Double valueMax) {
        this.valueMax = valueMax;
    }
    public Integer getNotificationLevel() {
        return notificationLevel;
    }

    public void setNotificationLevel(Integer notificationLevel) {
        this.notificationLevel = notificationLevel;
    }

    @Override
    public String toString() {
        return "DayOnDay{" +
            "dt=" + dt +
            ", db=" + db +
            ", tbl=" + tbl +
            ", value=" + value +
            ", valueMin=" + valueMin +
            ", valueMax=" + valueMax +
            ", notificationLevel=" + notificationLevel +
        "}";
    }
}
