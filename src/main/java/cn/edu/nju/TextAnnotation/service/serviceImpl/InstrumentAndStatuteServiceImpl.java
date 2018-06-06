package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;
import cn.edu.nju.TextAnnotation.repository.InstrumentAndStatueRepository;
import cn.edu.nju.TextAnnotation.service.InstrumentAndStatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentAndStatuteServiceImpl implements InstrumentAndStatuteService {
    @Autowired
    private InstrumentAndStatueRepository instrumentAndStatueRepository;

    public List<InstrumentAndStatute> getInstrumentAndStatute(String instrumentid){
        return instrumentAndStatueRepository.findByInstrumentid(instrumentid);
    }
}
