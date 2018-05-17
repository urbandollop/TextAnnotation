package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectManagementService {
    public List<ProjectVO> userAllProjects(int userid);
}
