package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.*;
import cn.edu.nju.TextAnnotation.model.Judgement;
import cn.edu.nju.TextAnnotation.service.FactService;
import cn.edu.nju.TextAnnotation.service.JudgeResultService;
import cn.edu.nju.TextAnnotation.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author :yangcui
 * @date:2018/5/16
 */
@Controller
@RequestMapping("/judge")
public class ShowAnnotationController {

    @Autowired
    private FactService factService;

    @Autowired
    private StatuteService statuteService;

    @Autowired
    private JudgeResultService judgeResultService;

    @RequestMapping(value = "/judgeAnnotation", method = RequestMethod.GET)

    public String displayAnnotation(Model model,@RequestParam(value = "iid", required = true) String instrumentid) {

//        List<FactListBean> factListBeans=factService.getAllFactByInstrumentId(instrumentid);
        ListBean listBean = factService.getAllFactByInstrumentID(instrumentid);
        model.addAttribute("listBean", listBean);

        List<StatuteListBean> statuteListBeans=statuteService.getAllStatuteByStatuteid(instrumentid);
        model.addAttribute("statuteListBeans", statuteListBeans);

        return "/judge/judgeAnnotation";
    }
    @PostMapping("/annotationSubmit.action")
    public @ResponseBody
    ResultMessageBean annotationSubmit(@RequestBody List<JudgementListBean> judgementListBeans){
        if(judgementListBeans!=null){
            for (int i=0;i<judgementListBeans.size();i++){
                System.out.println("===============");
                Judgement judgement=new Judgement();
                judgement.setJudgement_id(judgementListBeans.get(i).getJudementid());
                judgement.setUserId(judgementListBeans.get(i).getUserid());
                judgement.setFactId(judgementListBeans.get(i).getFactid());
                judgement.setStatuteId(judgementListBeans.get(i).getStatuteid());
                judgement.setIsrelated(judgementListBeans.get(i).getIsrelated());
                judgement.setProjectId(judgementListBeans.get(i).getProjectid());
                judgeResultService.saveJudgement(judgement);
            }
            return  new ResultMessageBean(ResultMessageBean.SUCCESS, "提交成功");
        }
        else{
            return  new ResultMessageBean(ResultMessageBean.ERROR, "提交失败");
        }

    }


}
