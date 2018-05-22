package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;

import java.util.List;


public interface InstrumentManagementService {
    public List<InstrumentVO> userAllInstruments(int userid,int projectid);
}
