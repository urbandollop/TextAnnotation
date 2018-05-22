package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.model.Instrument;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.repository.InstrumentRepository;
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
}
