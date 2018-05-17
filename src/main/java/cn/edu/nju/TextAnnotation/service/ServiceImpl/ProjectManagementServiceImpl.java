package cn.edu.nju.TextAnnotation.service.ServiceImpl;

import cn.edu.nju.TextAnnotation.bean.ProjectVO;
import cn.edu.nju.TextAnnotation.model.Project;
import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.ProjectRepository;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.ProjectManagementService;
import cn.edu.nju.TextAnnotation.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
