package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;

public class InstrumentAndStatuteBean {
    public String instrument_id;
    public String statute_id;
    public Integer num;
    public InstrumentAndStatuteBean(InstrumentAndStatute instrumentAndStatute){
        this.instrument_id=instrumentAndStatute.getInstrumentid();
        this.statute_id=instrumentAndStatute.getStatuteid();
        this.num=instrumentAndStatute.getNum();
    }
}
