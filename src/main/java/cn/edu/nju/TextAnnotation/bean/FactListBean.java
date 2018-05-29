package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Fact;

public class FactListBean {
    public  Integer factid;
    public  String instrumentid;
    public  String text;
    public  Integer num;
    public FactListBean(Fact fact){
        this.factid=fact.getFactid();
        this.instrumentid=fact.getInstrumentid().toString();
        this.text=fact.getText().toString();
        this.num=fact.getNum();
    }

}
