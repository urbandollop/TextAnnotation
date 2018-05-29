package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.StatuteListBean;

import java.util.List;

public interface StatuteService {
   List<StatuteListBean>  getAllStatuteByStatuteid(String instrumentid);
}
