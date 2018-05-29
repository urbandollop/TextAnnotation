package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.ShowAnnotationBean;

import java.util.List;

public interface ShowAnnotationService {
   public List<ShowAnnotationBean> getAnnotationInfo(String instrumentid);
}
