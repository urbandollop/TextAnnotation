package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(InstrumentAndStatuteIdClass.class)
@Table(name="instrumentandstatute")
public class InstrumentAndStatute implements Serializable{
    private static final long serialVersionUID = -906357110051689484L;
    @Id
    @Column(name="instrument_id")
    private String instrumentid;
    @Id
    @Column (name="statute_id")
    private String statuteid;
    @Column(name="num")
    private Integer num;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "InstrumentAndStatute{" +
                "文书名=" + instrumentid +
                ", 法条名=" + statuteid +
                ", num=" + num +'}';
    }
}
