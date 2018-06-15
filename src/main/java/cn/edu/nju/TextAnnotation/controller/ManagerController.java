package cn.edu.nju.TextAnnotation.controller;

import cn.edu.nju.TextAnnotation.bean.*;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.service.ProjectManagementService;
import cn.edu.nju.TextAnnotation.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author keenan on 2018/5/29
 */
@Controller
public class ManagerController {
    @Autowired
    private ProjectManagementService projectManagementService;
    @Autowired
    private UserService userService;

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
            modelAndView.addObject("users", userService.getAllNormalUsers());
        }
        return modelAndView;
    }

    @GetMapping("/manager/users")
    public ModelAndView getAllUsers() {
        // TODO 填上资源地址
        ModelAndView modelAndView = new ModelAndView("/manager/users");
        modelAndView.addObject("users", userService.getAllNormalUsers());
        return modelAndView;
    }

    @GetMapping("/manager/modifyAllocate")
    public ModelAndView showModifyAllocate(@RequestParam(value = "projectId", required = true) Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("manager/modifyAllocate");
        Map<Boolean, List<UserBean>> userInfo = projectManagementService.getUserTaskAllocationInfo(projectId);
        ProjectVO projectVO = projectManagementService.findProjectByProjectId(projectId);
        modelAndView.addObject("project", projectVO);
        modelAndView.addObject("allocatedUsers", userInfo.get(true));
        modelAndView.addObject("unallocatedUsers", userInfo.get(false));
        return modelAndView;
    }

    @PostMapping("/manager/modify")
    public @ResponseBody
    ResultMessageBean modifyPasswd(@RequestParam Integer userid, @RequestParam String passwd) {
        return userService.modify(userid, passwd);
    }

    @PostMapping("/manager/addUser.action")
    public @ResponseBody
    ResultMessageBean addUser(@RequestBody NewUserBean newUserBean) {
        return userService.signUp(newUserBean);
    }

    @PostMapping("/manager/createProject.action")
    public @ResponseBody
    ResultMessageBean createProject(@RequestBody NewProjectBean newProjectBean) {
        return projectManagementService.createProject(newProjectBean);
    }

    @PostMapping("/manager/allocate.action")
    public @ResponseBody
    ResultMessageBean allocateTask(@RequestBody List<TaskAllocationBean> allocationBeans) {
        return projectManagementService.allocateTask(allocationBeans);
    }

}
