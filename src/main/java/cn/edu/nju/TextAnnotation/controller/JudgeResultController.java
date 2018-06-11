package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.JudgeResultBean;
import cn.edu.nju.TextAnnotation.service.JudgeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JudgeResultController {
    @Autowired
    private JudgeResultService judgeResultService;

    @GetMapping("/manager/result")
    public ModelAndView getProjectJudgement(@RequestParam(value = "projectId", required = true) Integer projectId) {
        List<JudgeResultBean> judRes = judgeResultService.findJudgeResult(projectId);
        ModelAndView modelAndView = new ModelAndView("/manager/judgeResult");
        modelAndView.addObject("judRes", judRes);
        return modelAndView;
    }
}
