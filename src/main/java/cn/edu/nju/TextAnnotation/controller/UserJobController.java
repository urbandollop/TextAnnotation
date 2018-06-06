package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.InstrumentVO;
import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import cn.edu.nju.TextAnnotation.model.Project;
import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.security.SystemUserDetailsService;
import cn.edu.nju.TextAnnotation.service.InstrumentManagementService;
import cn.edu.nju.TextAnnotation.service.ProjectManagementService;
import cn.edu.nju.TextAnnotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserJobController {
    @Autowired
    ProjectManagementService projectManagementService;
    @Autowired
    UserService userService;
    @Autowired
    InstrumentManagementService instrumentManagementService;

    @GetMapping("/user/index")
    public ModelAndView showProjects(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/user/projectList");
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            List<ProjectVO> allProjects = projectManagementService.userAllProjects(currentUser.getUser_id());
            modelAndView.addObject("allProjects", allProjects);
        }
        return modelAndView;
    }

    @GetMapping("/user/judgementList")
    public ModelAndView showJudgementList(@RequestParam(value = "pid", required = true) Integer pid) {
        ModelAndView modelAndView = new ModelAndView("/user/instrumentList");
        User currentUser = userService.getCurrentUser();
        List<InstrumentVO> instrumentVOS = instrumentManagementService.userAllInstruments(currentUser.getUser_id(), pid);
        modelAndView.addObject("allInstuments", instrumentVOS);
        return modelAndView;
    }

}
