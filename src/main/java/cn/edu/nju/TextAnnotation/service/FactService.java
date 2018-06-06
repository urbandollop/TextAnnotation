package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.ListBean;

import java.util.List;

public interface FactService {
    public List<FactListBean> getAllFactByInstrumentId(String instrumentid);

    public ListBean getAllFactByInstrumentID(String instrumentid);
}
