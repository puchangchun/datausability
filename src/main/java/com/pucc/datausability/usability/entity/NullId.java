package com.pucc.datausability.usability.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 空值指标表
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@TableName("null_id")
public class NullId implements Serializable {

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
     * 列名
     */
    private String col;

    /**
     * 空ID个数
     */
    private Integer value;

    /**
     * 下限
     */
    private Integer valueMin;

    /**
     * 上限
     */
    private Integer valueMax;

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
    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public Integer getValueMin() {
        return valueMin;
    }

    public void setValueMin(Integer valueMin) {
        this.valueMin = valueMin;
    }
    public Integer getValueMax() {
        return valueMax;
    }

    public void setValueMax(Integer valueMax) {
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
        return "NullId{" +
            "dt=" + dt +
            ", db=" + db +
            ", tbl=" + tbl +
            ", col=" + col +
            ", value=" + value +
            ", valueMin=" + valueMin +
            ", valueMax=" + valueMax +
            ", notificationLevel=" + notificationLevel +
        "}";
    }
}
