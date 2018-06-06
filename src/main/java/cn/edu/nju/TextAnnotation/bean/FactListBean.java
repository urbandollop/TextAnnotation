package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Fact;

import java.util.List;

public class FactListBean {
    public  Integer factid;
    public  String instrumentid;
    public  String text;
    public  Integer num;
    List<FactListBean> factListBeanList;
    public FactListBean(){
    }
    public FactListBean(Fact fact){
        this.factid=fact.getFactid();
        this.instrumentid=fact.getInstrumentid().toString();
        this.text=fact.getText().toString();
        this.num=fact.getNum();
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

    public List<FactListBean> getFactListBeanList() {
        return factListBeanList;
    }
    public void setFactListBeanList(List<FactListBean> factListBeanList) {
        this.factListBeanList = factListBeanList;
    }
}
