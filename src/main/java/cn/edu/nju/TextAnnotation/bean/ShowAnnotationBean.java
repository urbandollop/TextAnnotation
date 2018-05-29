package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Fact;
import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;
import cn.edu.nju.TextAnnotation.model.Statute;

public class ShowAnnotationBean {
    /**
     * 事实所属文件的名称
     */
    public  String instrument_id;
    /**
     * 事实的顺序
     */
    public  Integer num;
    /**
     * 事实的文本内容
     */
    public  String  facttext;
    /**
     * 法条的名称
     */
    public String  name;
    /**
     * 法条的内容
     */
    public String statutetext;

    /**
     * ShowAnnotationBean的构造方法
     * @param fact
     * @param instrumentAndStatute
     * @param statute
     */
    public ShowAnnotationBean(Fact fact, InstrumentAndStatute instrumentAndStatute , Statute statute){
        this.instrument_id=fact.getInstrumentid();
        this.num=fact.getNum();
        this.facttext=fact.getText();
        this.name=statute.getName();
        this.name=statute.getText();
    }

}
