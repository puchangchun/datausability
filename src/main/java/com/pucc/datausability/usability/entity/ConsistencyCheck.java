package com.pucc.datausability.usability.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pucc
 * @since 2023-03-02
 */
@TableName("consistency_check")
public class ConsistencyCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据时间分区dt
     */
    private String dataDate;

    /**
     * 库名
     */
    private String databaseName;

    /**
     * 数据源表表名
     */
    private String sourceTableName;

    /**
     * 数据源表字段名
     */
    private String sourceColumn;

    /**
     * 数据目标表表名
     */
    private String targetTableName;

    /**
     * 数据目标表字段名
     */
    private String targetColumn;

    /**
     * 全表数据一致记录数
     */
    private Integer consistentDataCount;

    /**
     * 数据源表全表记录数
     */
    private Integer sourceTableCount;

    /**
     * 数据目标表全表记录数
     */
    private Integer targetTableCount;

    /**
     * 数据目标表重复记录数
     */
    private Integer targetDuplicateCount;

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public String getSourceTableName() {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }
    public String getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn;
    }
    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }
    public String getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
    }
    public Integer getConsistentDataCount() {
        return consistentDataCount;
    }

    public void setConsistentDataCount(Integer consistentDataCount) {
        this.consistentDataCount = consistentDataCount;
    }
    public Integer getSourceTableCount() {
        return sourceTableCount;
    }

    public void setSourceTableCount(Integer sourceTableCount) {
        this.sourceTableCount = sourceTableCount;
    }
    public Integer getTargetTableCount() {
        return targetTableCount;
    }

    public void setTargetTableCount(Integer targetTableCount) {
        this.targetTableCount = targetTableCount;
    }
    public Integer getTargetDuplicateCount() {
        return targetDuplicateCount;
    }

    public void setTargetDuplicateCount(Integer targetDuplicateCount) {
        this.targetDuplicateCount = targetDuplicateCount;
    }

    @Override
    public String toString() {
        return "ConsistencyCheck{" +
            "dataDate=" + dataDate +
            ", databaseName=" + databaseName +
            ", sourceTableName=" + sourceTableName +
            ", sourceColumn=" + sourceColumn +
            ", targetTableName=" + targetTableName +
            ", targetColumn=" + targetColumn +
            ", consistentDataCount=" + consistentDataCount +
            ", sourceTableCount=" + sourceTableCount +
            ", targetTableCount=" + targetTableCount +
            ", targetDuplicateCount=" + targetDuplicateCount +
        "}";
    }
}
