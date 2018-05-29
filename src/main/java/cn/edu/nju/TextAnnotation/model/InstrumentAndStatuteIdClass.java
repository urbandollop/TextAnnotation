package cn.edu.nju.TextAnnotation.model;

import java.io.Serializable;

/**
 * @Author:yangcui
 * @date :2018/5/21
 * 联合主键类
 */
public class InstrumentAndStatuteIdClass implements Serializable {
    private String instrumentid;
    private String statuteid;

    public String getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(String instrumentid) {
        this.instrumentid = instrumentid;
    }

    public String getStatuteid() {
        return statuteid;
    }

    public void setStatuteid(String statuteid) {
        this.statuteid = statuteid;
    }



}
