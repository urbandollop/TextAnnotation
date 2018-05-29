package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;

import java.util.List;

public interface InstrumentAndStatuteService {
    public List<InstrumentAndStatute> getInstrumentAndStatute(String instrumentid);
}
