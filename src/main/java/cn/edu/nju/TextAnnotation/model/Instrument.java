package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {
    @Id
    @Column(name = "instrument_id")
    private String instrumentid;
    @Column
    private String xml;
    @Column
    private Integer num;

    public Instrument(){}

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
