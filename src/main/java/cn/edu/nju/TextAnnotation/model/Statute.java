package cn.edu.nju.TextAnnotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statute")
public class Statute {
    @Id
    @Column(name="statute_id")
    private String statuteid;
    @Column(name="name")
    private String name;
    @Column(name="text")
    private String text;

    public Statute(){

    }
    public Statute(String statuteid,String name,String text){
        this.statuteid=statuteid;
        this.name=name;
        this.text=text;
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
}
