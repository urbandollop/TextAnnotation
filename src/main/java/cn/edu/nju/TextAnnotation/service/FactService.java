package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.FactListBean;

import java.util.List;

public interface FactService {
    public List<FactListBean> getAllFactByInstrumentId(String instrumentid);
}
