package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import cn.edu.nju.TextAnnotation.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectManagementService {
    public List<ProjectVO> userAllProjects(int userid);

    List<ProjectVO> findAllProjects();

    ProjectVO findProjectByProjectId(Integer projectId);;
}
