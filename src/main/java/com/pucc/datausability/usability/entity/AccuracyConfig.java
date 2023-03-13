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
@TableName("accuracy_config")
public class AccuracyConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库名
     */
    private String db;

    /**
     * 表名
     */
    private String tbl;

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

    @Override
    public String toString() {
        return "AccuracyConfig{" +
            "db=" + db +
            ", tbl=" + tbl +
        "}";
    }
}
