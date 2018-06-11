package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.JudgeResultBean;
import cn.edu.nju.TextAnnotation.model.Judgement;
import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.JudgementRepository;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.JudgeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JudgeResultServiceImpl implements JudgeResultService {
    @Autowired
    private JudgementRepository judgementRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<JudgeResultBean> findJudgeResult(int projectId) {
        Map<Integer, String> userName = new HashMap<>();
        Map<Integer, Boolean> userFinished = new HashMap<>();
        List<JudgeResultBean> judgeResultBeans = new ArrayList<>();
        List<Judgement> judgements = judgementRepository.findAllByProjectId(projectId);
        String name = "";
        User user;
        for (Judgement j : judgements) {
            int id = j.getUserId();
            if (userName.containsKey(id)) {
                name = userName.get(id);
            } else {
                user = userRepository.findUserByUserid(id);
                if (user != null) {
                    name = user.getName();
                    userName.put(id, name);
                }
            }
            int res = j.getIsrelated();
            if (res == -1) {
                userFinished.put(id, false);
            } else if (!userFinished.containsKey(id)) {
                userFinished.put(id, true);
            }
        }
        for (Map.Entry<Integer, String> entry : userName.entrySet()) {
            judgeResultBeans.add(new JudgeResultBean(entry.getKey(), entry.getValue(), userFinished.get(entry.getKey())));
        }
        return judgeResultBeans;
    }

    @Override
    public String saveJudgement(Judgement j) {
        Judgement judgement = judgementRepository.findFirstByUserIdAndFactIdAndStatuteIdAndProjectId(j.getUserId(), j.getFactId(), j.getStatuteId(), j.getProjectId());
        if (judgement == null) {
            judgementRepository.save(j);
            return "分配成功";
        } else {
            judgement.setIsrelated(j.getIsrelated());
            judgementRepository.save(judgement);
            return "标注jid=" + judgement.getJudgement_id() + ",成功!";
        }
    }
}
