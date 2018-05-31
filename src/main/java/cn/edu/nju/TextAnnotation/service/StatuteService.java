package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.model.Statute;

import java.util.List;

public interface StatuteService {
   List<StatuteListBean>  getAllStatuteByStatuteid(String instrumentid);
   List<StatuteListBean> getAllStatute();
   StatuteListBean getOneById(String id);
}
