package com.pucc.datausability.usability.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pucc
 * @since 2023-03-07
 */
@TableName("detect_repair_config")
public class DetectRepairConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dt;

    private String db;

    private String tbl;

    private String res;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
    public String getDb() {
        return db;
    }

    public void setDb(String tb) {
        this.db = tb;
    }
    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }
    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "DetectRepairConfig{" +
            "dt=" + dt +
            ", db=" + db +
            ", tbl=" + tbl +
            ", res=" + res +
        "}";
    }
}
