package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.service.FactService;
import cn.edu.nju.TextAnnotation.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author :yangcui
 * @date:2018/5/16
 */
@Controller
@RequestMapping("/judgeAnnotation")
public class ShowAnnotationController {
    @Autowired
    private FactService factService;
    @Autowired
    private StatuteService statuteService;
    @RequestMapping(value = "/judge", method = RequestMethod.GET)
    public String displayShowPlan(Model model) {
        String instrumentid="006_376154.xml_ft2jl.xls_xsys";
        List<FactListBean> factListBeans=factService.getAllFactByInstrumentId(instrumentid);
        model.addAttribute("factListBeans", factListBeans);

        List<StatuteListBean> statuteListBeans=statuteService.getAllStatuteByStatuteid(instrumentid);
        model.addAttribute("statuteListBeans", statuteListBeans);

        return "judge/judgeAnnotation";
    }

}
