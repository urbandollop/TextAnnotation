package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.model.Fact;
import cn.edu.nju.TextAnnotation.model.Instrument;
import cn.edu.nju.TextAnnotation.model.Judgement;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.repository.FactRepository;
import cn.edu.nju.TextAnnotation.repository.InstrumentRepository;
import cn.edu.nju.TextAnnotation.repository.JudgementRepository;
import cn.edu.nju.TextAnnotation.repository.ProjectRepository;
import cn.edu.nju.TextAnnotation.service.InstrumentManagementService;
import cn.edu.nju.TextAnnotation.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstrumentManagementServiceImpl implements InstrumentManagementService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskManagementService taskManagementService;
    @Autowired
    InstrumentRepository instrumentRepository;
    @Autowired
    FactRepository factRepository;
    @Autowired
    JudgementRepository judgementRepository;

    @Override
    public List<InstrumentVO> userAllInstruments(int userid, int projectid) {
        Task task = taskManagementService.findTaskOfUseridAndProjectid(userid,projectid);
        int min=task.getBegin();
        int max = task.getEnd();
        List<InstrumentVO> instrumentVOs = new ArrayList<>();
        List<Instrument> instruments = instrumentRepository.findInstrumentsByNumBetween(min, max);
        instruments.sort(Comparator.comparing(Instrument::getNum));
        instruments.forEach(instrument -> instrumentVOs.add(new InstrumentVO(instrument)));
        return instrumentVOs;
    }

    //获取未标注文书列表
    @Override
    public List<InstrumentVO> userAllInstumentsWithNoJudge(int userid,int projectid){
        List<InstrumentVO> instrumentVOsList = new ArrayList<>();
        List<InstrumentVO> instrumentVOs = userAllInstruments(userid,projectid);
        for(int i=0;i<instrumentVOs.size();i++) {
            Fact fact = factRepository.findFirstByInstrumentidAndNum(instrumentVOs.get(i).getInstrumentid(), 1);
            if (fact != null) {
                Judgement judgement = judgementRepository.findFirstByUserIdAndFactIdAndProjectId(userid, fact.getFactid(), projectid);
                if(judgement!=null) {
                    if (judgement.getIsrelated() == -1) {
                        instrumentVOsList.add(instrumentVOs.get(i));
                    }
                }
            }
        }
        return  instrumentVOsList;
    }

    //获取已标注文书列表
    @Override
    public List<InstrumentVO> userAllInstumentsWithJudge(int userid,int projectid) {
        List<InstrumentVO> instrumentVOsList = new ArrayList<>();
        List<InstrumentVO> instrumentVOs = userAllInstruments(userid, projectid);
        for (int i = 0; i < instrumentVOs.size(); i++) {
            Fact fact = factRepository.findFirstByInstrumentidAndNum(instrumentVOs.get(i).getInstrumentid(), 1);
            if (fact != null) {
                Judgement judgement = judgementRepository.findFirstByUserIdAndFactIdAndProjectId(userid, fact.getFactid(), projectid);
                if(judgement!=null) {
                    if (judgement.getIsrelated() != -1) {
                        instrumentVOsList.add(instrumentVOs.get(i));
                    }
                }
            }
        }
        return instrumentVOsList;
    }
}
