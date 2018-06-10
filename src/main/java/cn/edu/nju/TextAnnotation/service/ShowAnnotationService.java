package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.bean.ShowAnnotationBean;
import cn.edu.nju.TextAnnotation.model.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ShowAnnotationService {
//   public List<ShowAnnotationBean> getAnnotationInfo(String instrumentid);

   public InstrumentVO getNextInstrument(String instrumentid, Integer userid, Integer projectid);
}
