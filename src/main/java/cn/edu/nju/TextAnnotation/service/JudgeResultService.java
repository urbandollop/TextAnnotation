package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.JudgeResultBean;
import cn.edu.nju.TextAnnotation.model.Judgement;

import java.util.List;
import java.util.Map;

public interface JudgeResultService {
    public List<JudgeResultBean> findJudgeResult(int projectId);

    public String saveJudgement(Judgement j);
}
