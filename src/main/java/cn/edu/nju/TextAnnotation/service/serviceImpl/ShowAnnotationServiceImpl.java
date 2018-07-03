package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.bean.JudgementListBean;
import cn.edu.nju.TextAnnotation.model.*;
import cn.edu.nju.TextAnnotation.repository.*;
import cn.edu.nju.TextAnnotation.service.ShowAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAnnotationServiceImpl implements ShowAnnotationService {
    @Autowired
    InstrumentRepository instrumentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private JudgementRepository judgementRepository;
    @Autowired
    private FactRepository factRepository;
    @Autowired
    private InstrumentAndStatueRepository instrumentAndStatueRepository;

    @Override
    public InstrumentVO getNextInstrument(String instrumentid, Integer userid, Integer projectid){

        Instrument Instrument=instrumentRepository.findFirstByInstrumentid(instrumentid);
        int num = Instrument.getNum();
        Task task =taskRepository.findFirstByUseridAndProjectid(userid,projectid);
        if(num==task.getEnd())
        {
            InstrumentVO instrumentVO=new InstrumentVO(Instrument);
            return instrumentVO;
        }
        else
        {
            num=num+1;
            Instrument=instrumentRepository.findFirstByNum(num);
            InstrumentVO instrumentVO=new InstrumentVO(Instrument);
            return instrumentVO;
        }
    }

    @Override
    public InstrumentVO getThisInstrument(String instrumentid){
        Instrument Instrument=instrumentRepository.findFirstByInstrumentid(instrumentid);
        InstrumentVO instrumentVO=new InstrumentVO(Instrument);
        return instrumentVO;
    }

    @Override
    public List<JudgementListBean> getJudgementList(String instrumentid, Integer userid, Integer projectid){
        List<JudgementListBean> judgementListBeanList=new ArrayList<>();
        List<Fact> factList =factRepository.findByInstrumentid(instrumentid);
        List<InstrumentAndStatute> instrumentAndStatuteList=instrumentAndStatueRepository.findByInstrumentid(instrumentid);
        for(int i=0;i<factList.size();i++)
        {
            int fid=factList.get(i).getFactid();
            for(int j =0;j<instrumentAndStatuteList.size();j++) {
                String sid=instrumentAndStatuteList.get(j).getStatuteid();
                Judgement judgement = judgementRepository.findFirstByUserIdAndFactIdAndStatuteIdAndProjectId(userid,fid,sid,projectid);
                JudgementListBean judgementListBean =new JudgementListBean();
                judgementListBean.setFactid(judgement.getFactId());
                judgementListBean.setIsrelated(judgement.getIsrelated());
                judgementListBean.setJudementid(judgement.getJudgement_id());
                judgementListBean.setStatuteid(judgement.getStatuteId());
                judgementListBean.setProjectid(judgement.getProjectId());
                judgementListBeanList.add(judgementListBean);
            }
        }
        return judgementListBeanList;
    }
}
