package cn.edu.nju.TextAnnotation.service.serviceImpl;


import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;
import cn.edu.nju.TextAnnotation.model.Statute;
import cn.edu.nju.TextAnnotation.repository.InstrumentAndStatueRepository;
import cn.edu.nju.TextAnnotation.repository.StatuteRepository;
import cn.edu.nju.TextAnnotation.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :yangcui
 * @date:2018/5/18
 */
@Service
public class StatuteServiceImpl implements StatuteService {
    @Autowired
    private InstrumentAndStatueRepository instrumentAndStatueRepository;
    @Autowired
    private StatuteRepository statuteRepository;

    private String statuteid;

    /**
     * 先通过instrumentid找到这篇文书引用的所有法条id（通过InstrumentAndStatute表)
     * 再通过法条id在Statute找到对应的法条名称
     * @param instrumentid
     * @return
     */
    @Override
    public List<StatuteListBean> getAllStatuteByStatuteid(String instrumentid){
        List<InstrumentAndStatute> instrumentAndStatutes=instrumentAndStatueRepository.findByInstrumentid(instrumentid);
        for(int j=0;j<instrumentAndStatutes.size();j++){
            InstrumentAndStatute instrumentAndStatute= instrumentAndStatutes.get(j);
        }
        List<StatuteListBean> statuteListBeans=new ArrayList<>();
        for (int i=0;i<instrumentAndStatutes.size();i++){
            InstrumentAndStatute instrumentAndStatute= instrumentAndStatutes.get(i);
            statuteid=instrumentAndStatute.getStatuteid();
            Statute statute= statuteRepository.findByStatuteid(statuteid);
            statuteListBeans.add(new StatuteListBean(statute));
        }
        return statuteListBeans;
    }
    @Override
    public List<StatuteListBean> getAllStatute(){
        List<StatuteListBean> statuteListBeans = new ArrayList<>();
        List<Statute> statutes = statuteRepository.findAll();
        for(int i =0;i<statutes.size();i++)
        {
            StatuteListBean statuteListBean =new StatuteListBean(statutes.get(i)) ;
            statuteListBeans.add(statuteListBean);
        }
        return statuteListBeans;
    }
    @Override
    public StatuteListBean getOneById(String id){
        Statute statute = statuteRepository.findByStatuteid(id);
        StatuteListBean statuteListBean =new StatuteListBean(statute);
        return  statuteListBean;
    }

}
