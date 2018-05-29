package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Statute;

public class StatuteListBean {
    public  String statuteid;
    public  String name;
    public  String text;

    public StatuteListBean(Statute statute){
        this.statuteid=statute.getStatuteid();
        this.name=statute.getName();
        this.text=statute.getText();
    }
}
