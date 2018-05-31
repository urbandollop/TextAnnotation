package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.NewProjectBean;
import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import cn.edu.nju.TextAnnotation.bean.ResultMessageBean;
import cn.edu.nju.TextAnnotation.model.Project;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.repository.ProjectRepository;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.ProjectManagementService;
import cn.edu.nju.TextAnnotation.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskManagementService taskManagementService;

    @Override
    public List<ProjectVO> userAllProjects(int userid) {
        List<Task> tasks = taskManagementService.findTaskOfUser(userid);
        Set<Integer> p_ids = new HashSet<>();
        List<ProjectVO> projects = new ArrayList<>();
        for (Task t : tasks) {
            p_ids.add(t.getProject_id());
        }
        for (Integer i : p_ids) {
            Optional<Project> project = projectRepository.findById(i);
            project.ifPresent(e -> {
                projects.add(ProjectVO.fromProject(e));
            });
        }
        return projects;
    }

    @Override
    public List<ProjectVO> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        Collections.sort(projects, (o1, o2) -> o2.getStarttime().compareTo(o1.getStarttime()));
        List<ProjectVO> projectVOS = new ArrayList<>();
        projects.forEach(project -> projectVOS.add(ProjectVO.fromProject(project)));
        return projectVOS;
    }

    @Override
    public ProjectVO findProjectByProjectId(Integer projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            return null;
        } else {
            return ProjectVO.fromProject(project.get());
        }
    }

    @Override
    public ResultMessageBean createProject(NewProjectBean newProjectBean) {
        try {
            Project project = new Project();
            project.setAllocated(false);
            project.setName(newProjectBean.projectName);
            project.setStarttime(LocalDateTime.now());
            projectRepository.save(project);
            return new ResultMessageBean(ResultMessageBean.SUCCESS);
        } catch (Exception e) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "创建失败");
        }
    }
}
