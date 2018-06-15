package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ProjectManagementService {
    public List<ProjectVO> userAllProjects(int userid);

    List<ProjectVO> findAllProjects();

    ProjectVO findProjectByProjectId(Integer projectId);;

    ResultMessageBean createProject(NewProjectBean newProjectBean);

    ResultMessageBean allocateTask(List<TaskAllocationBean> allocationBeans);

    /**
     * 获得项目目前的分配情况（修改分配时要显示已分配的情况和未分配的用户信息）
     * @param projectId
     * @return
     */
    Map<Boolean, List<UserBean>> getUserTaskAllocationInfo(Integer projectId);
}
