package cn.edu.nju.TextAnnotation.service;

import java.util.List;
import java.util.Map;

public interface JudgeResultService {
    public Map<Integer, Boolean> findJudgeResult(int projectId);
}
