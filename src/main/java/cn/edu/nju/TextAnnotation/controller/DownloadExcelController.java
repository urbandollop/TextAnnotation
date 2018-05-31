package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.model.Statute;
import cn.edu.nju.TextAnnotation.service.ExcelService;
import cn.edu.nju.TextAnnotation.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/export")
public class DownloadExcelController {
    @Autowired
    ExcelService excelService;
    @Autowired
    StatuteService statuteService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showStatuteList(Model model){
        List<StatuteListBean> statuteListBeans = statuteService.getAllStatute();
        model.addAttribute("allStatutes",statuteListBeans);
        return "/excel/showStatuteList";
    }

    @RequestMapping(value = "/facts",method = RequestMethod.GET)
    public String showExport(Model model, @RequestParam(value = "sid", required = true) String sid,HttpSession session){
        StatuteListBean statuteListBean = statuteService.getOneById(sid);
        String sname = statuteListBean.getName();
        session.setAttribute("sname",sname);
        session.setAttribute("sid",sid);
        List<FactListBean> factListBeans = excelService.getAllFromFactWhereStatuteId(sid);
        model.addAttribute("allFacts",factListBeans);
        return "/excel/export";
    }
    @PostMapping(value = "/download")
    public  String  downloadExcel(HttpSession session,HttpServletResponse response)  {
        String sid = session.getAttribute("sid").toString();
        List<FactListBean> factListBeans = excelService.getAllFromFactWhereStatuteId(sid);
        StatuteListBean statuteListBean = statuteService.getOneById(sid);
        String sname = statuteListBean.getName();
        try {
            excelService.downloadExportExcel(response,sname,factListBeans);
            return "/excel/export";
        }catch(Exception e){
            return "/excel/export";        }
    }
//    public String showExport(Model model){
//        String sname = "中华人民共和国刑法(2015)第一百三十三条";
//        List<FactListBean> factListBeans = excelService.getAllFromFactWhereStatuteName(sname);
//        model.addAttribute("allFacts",factListBeans);
//        return "/excel/export";
//    }

//@PostMapping(value = "/export/download")
//public  String  downloadExcel(HttpServletResponse response)  {
//        String sname ="中华人民共和国刑法(2015)第一百三十三条";
//    List<FactListBean> factListBeans = excelService.getAllFromFactWhereStatuteName(sname);
//    try {
//        excelService.downloadExportExcel(response,sname,factListBeans);
//        return "/excel/export";
//    }catch(Exception e){
//        return "/excel/export";        }
//
//}


}
