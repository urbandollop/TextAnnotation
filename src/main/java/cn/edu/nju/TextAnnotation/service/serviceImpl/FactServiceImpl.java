package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.ListBean;
import cn.edu.nju.TextAnnotation.model.Fact;
import cn.edu.nju.TextAnnotation.repository.FactRepository;
import cn.edu.nju.TextAnnotation.service.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactServiceImpl implements FactService {
    @Autowired
    private FactRepository factRepository;
    @Override
    public List<FactListBean> getAllFactByInstrumentId(String instrumentid){
         List<Fact> facts=factRepository.findByInstrumentid(instrumentid);
         for (int i=0;i<facts.size();i++){
            Fact fact=facts.get(i);
         }
         List<FactListBean> factListBeans=new ArrayList<>();
         facts.stream().forEach(fact -> factListBeans.add(new FactListBean(fact)));
         return  factListBeans;
    }

    @Override
    public ListBean getAllFactByInstrumentID(String instrumentid) {
        List<FactListBean> list = getAllFactByInstrumentId(instrumentid);
        ListBean listBean = new ListBean();
        listBean.setList(list);
        return listBean;
    }
}
