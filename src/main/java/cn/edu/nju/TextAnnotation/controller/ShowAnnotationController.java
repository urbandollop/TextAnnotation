package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.*;
import cn.edu.nju.TextAnnotation.model.Judgement;
import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.service.*;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ShowAnnotationService showAnnotationService;

    @RequestMapping(value = "/judgeAnnotation", method = RequestMethod.GET)

    public String displayAnnotation(Model model,@RequestParam(value = "iid", required = true) String instrumentid,@RequestParam(value = "pid", required = true) Integer projectid) {
        User user = userService.getCurrentUser();
        int uid =user.getUser_id();
        InstrumentVO instrumentVO = showAnnotationService.getNextInstrument(instrumentid,uid,projectid);
        model.addAttribute("instumentNext",instrumentVO);
//        instrumentVO.getInstrumentid()instrumentVO.getNum()
//        List<FactListBean> factListBeans=factService.getAllFactByInstrumentId(instrumentid);
        ListBean listBean = factService.getAllFactByInstrumentID(instrumentid);
        model.addAttribute("listBean", listBean);

        List<StatuteListBean> statuteListBeans=statuteService.getAllStatuteByStatuteid(instrumentid);
        model.addAttribute("statuteListBeans", statuteListBeans);

        List<JudgementListBean> judgementListBeanList=showAnnotationService.getJudgementList(instrumentid,uid,projectid);
        model.addAttribute("judgementListBeans", judgementListBeanList);

        return "/user/judgeAnnotation";
    }
    @PostMapping("/annotationSubmit.action")
    public @ResponseBody
    ResultMessageBean annotationSubmit(@RequestBody List<JudgementListBean> judgementListBeans){
        User user = userService.getCurrentUser();
        String msg = "";
        if(judgementListBeans!=null){
            for (int i=0;i<judgementListBeans.size();i++){
//                System.out.println("===============");
                Judgement judgement=new Judgement();
                judgement.setUserId(user.getUser_id());
                judgement.setFactId(judgementListBeans.get(i).getFactid());
                judgement.setStatuteId(judgementListBeans.get(i).getStatuteid());
                judgement.setIsrelated(judgementListBeans.get(i).getIsrelated());
                judgement.setProjectId(judgementListBeans.get(i).getProjectid());
                 msg +=  judgeResultService.saveJudgement(judgement);
            }
            return  new ResultMessageBean(ResultMessageBean.ERROR, "提交成功");
        }
        else{
            return  new ResultMessageBean(ResultMessageBean.ERROR, "提交失败");
        }

    }


}
