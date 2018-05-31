package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.model.Judgement;

import java.util.List;
import java.util.Map;

public interface JudgeResultService {
    public Map<Integer, Boolean> findJudgeResult(int projectId);

    public boolean saveJudgement(Judgement j);
}
