package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.model.Judgement;
import cn.edu.nju.TextAnnotation.repository.JudgementRepository;
import cn.edu.nju.TextAnnotation.service.JudgeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JudgeResultServiceImpl implements JudgeResultService {
    @Autowired
    private JudgementRepository judgementRepository;

    @Override
    public Map<Integer, Boolean> findJudgeResult(int projectId) {
        Map<Integer, Boolean> userFinished = new HashMap<>();
        List<Judgement> judgements = judgementRepository.findAllByProjectId(projectId);
        for (Judgement j : judgements) {
            int id = j.getUserId();
            int res = j.getIsrelated();
            if (res == -1) {
                userFinished.put(id, false);
            } else if (!userFinished.containsKey(id)) {
                userFinished.put(id, true);
            }
        }
        return userFinished;
    }

    @Override
    public boolean saveJudgement(Judgement j) {
        if (j != null) {
            judgementRepository.save(j);
            return true;
        }
        return false;
    }
}
