package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.model.Instrument;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.repository.InstrumentRepository;
import cn.edu.nju.TextAnnotation.repository.TaskRepository;
import cn.edu.nju.TextAnnotation.service.ShowAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowAnnotationServiceImpl implements ShowAnnotationService {
    @Autowired
    InstrumentRepository instrumentRepository;
    @Autowired
    TaskRepository taskRepository;
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
}
