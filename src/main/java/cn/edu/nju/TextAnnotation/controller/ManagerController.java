package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import cn.edu.nju.TextAnnotation.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author keenan on 2018/5/29
 */
@Controller
public class ManagerController {
    @Autowired
    private ProjectManagementService projectManagementService;

    @GetMapping("/manager/index")
    public ModelAndView managerIndex() {
        ModelAndView modelAndView = new ModelAndView("/manager/projectList");
        modelAndView.addObject("allProjects", projectManagementService.findAllProjects());
        return modelAndView;
    }

    @GetMapping("/manager/allocate")
    public ModelAndView allocateTask(@RequestParam(value = "projectId", required = true) Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("/manager/taskAllocate");
        ProjectVO projectVO = projectManagementService.findProjectByProjectId(projectId);

        if (projectVO == null) {
            // TODO 如果项目不存在，返回404页面
            return null;
        }
        if (projectVO.getAllocated()) {
            // TODO 如果项目已分配，直接返回项目分配信息页面
            return null;
        } else {
            modelAndView.addObject("project", projectVO);
        }
        return modelAndView;
    }
}
