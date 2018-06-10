package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;

import java.util.List;


public interface InstrumentManagementService {
    public List<InstrumentVO> userAllInstruments(int userid,int projectid);

    public List<InstrumentVO> userAllInstumentsWithNoJudge(int userid,int projectid);

    public List<InstrumentVO> userAllInstumentsWithJudge(int userid,int projectid);
}
