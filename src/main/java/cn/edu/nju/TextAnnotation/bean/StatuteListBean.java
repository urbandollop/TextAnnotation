package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Statute;

import java.util.List;

public class StatuteListBean {
    public  String statuteid;
    public  String name;
    public  String text;
    public List<StatuteListBean> statuteListBeans;
    public StatuteListBean(){

    }
    public StatuteListBean(Statute statute){
        this.statuteid=statute.getStatuteid();
        this.name=statute.getName();
        this.text=statute.getText();
    }

    public String getStatuteid() {
        return statuteid;
    }

    public void setStatuteid(String statuteid) {
        this.statuteid = statuteid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<StatuteListBean> getStatuteListBeans() {
        return statuteListBeans;
    }

    public void setStatuteListBeans(List<StatuteListBean> statuteListBeans) {
        this.statuteListBeans = statuteListBeans;
    }
}
