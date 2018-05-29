package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;

@Entity
@Table(name="Fact")
public class Fact {
    @Id
    @Column(name="fact_id")
    @GeneratedValue
    private Integer factid;
    @Column(name="instrument_id")
    private String instrumentid;
    @Column(name="text")
    private String text;
    @Column(name="num")
    private Integer num;

    public Fact(){

    }
    public Fact(Integer factid,String instrumentid,String text,Integer num){
        this.factid=factid;
        this.instrumentid=instrumentid;
        this.text=text;
        this.num=num;
    }

    public Integer getFactid() {
        return factid;
    }

    public void setFactid(Integer factid) {
        this.factid = factid;
    }

    public String getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(String instrumentid) {
        this.instrumentid = instrumentid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "事实id=" + factid +
                ", 法条名=" + instrumentid +
                ", 法条内容=" + text +
                ",num="+num+'}';
    }
}
