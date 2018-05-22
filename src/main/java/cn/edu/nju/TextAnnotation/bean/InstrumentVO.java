package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Instrument;

public class InstrumentVO {
    private String instrumentid;
    private String xml;
    private Integer num;

    public InstrumentVO(){};
    public InstrumentVO(String instrumentid, String xml, Integer num) {
        this.instrumentid = instrumentid;
        this.xml = xml;
        this.num = num;
    }
    public InstrumentVO(Instrument instrument) {
        this.instrumentid =instrument.getInstrumentid();
        this.xml = instrument.getXml();
        this.num = instrument.getNum();
    }
    public String getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(String instrumentid) {
        this.instrumentid = instrumentid;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
