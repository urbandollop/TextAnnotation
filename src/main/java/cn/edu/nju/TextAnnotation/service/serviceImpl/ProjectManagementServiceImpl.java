package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.*;
import cn.edu.nju.TextAnnotation.model.*;
import cn.edu.nju.TextAnnotation.repository.InstrumentRepository;
import cn.edu.nju.TextAnnotation.repository.ProjectRepository;
import cn.edu.nju.TextAnnotation.repository.TaskRepository;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.*;
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
    @Autowired
    InstrumentRepository instrumentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    FactService factService;
    @Autowired
    StatuteService statuteService;
    @Autowired
    JudgeResultService judgeResultService;
    @Autowired
    UserService userService;

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

    @Override
    public ResultMessageBean allocateTask(List<TaskAllocationBean> allocationBeans) {
        Optional<Project> project;
        Task task;
        Judgement judgement;
        if (allocationBeans == null) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "任务分配失败");
        }
        try {
            project = projectRepository.findById(allocationBeans.get(0).projectId);
            if (!project.isPresent()) {
                return new ResultMessageBean(ResultMessageBean.ERROR, "不存在该项目");
            }
            for (TaskAllocationBean t : allocationBeans) {
                if (t.isAllocated) {
                    task = new Task();
                    task.setBegin(t.startNo);
                    task.setEnd(t.endNo);
                    task.setProject_id(t.projectId);
                    task.setUserid(t.userId);
                    taskManagementService.saveTask(task);

                    List<Instrument> instruments = instrumentRepository.findInstrumentsByNumBetween(t.startNo, t.endNo);
                    if (instruments != null) {
                        for (Instrument i : instruments) {
                            List<FactListBean> factListBeans = factService.getAllFactByInstrumentId(i.getInstrumentid());
                            List<StatuteListBean> statuteListBeans = statuteService.getAllStatuteByStatuteid(i.getInstrumentid());
                            if (factListBeans != null && factListBeans.size() > 0 && statuteListBeans != null && statuteListBeans.size() > 0) {
                                for (FactListBean f : factListBeans) {
                                    for (StatuteListBean s : statuteListBeans) {
                                        judgement = new Judgement();
                                        judgement.setFactId(f.factid);
                                        judgement.setProjectId(t.projectId);
                                        judgement.setIsrelated(-1);
                                        judgement.setStatuteId(s.statuteid);
                                        judgement.setUserId(t.userId);
                                        judgeResultService.saveJudgement(judgement);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Project proget = project.get();
            proget.setAllocated(true);
            projectRepository.save(proget);
            return new ResultMessageBean(ResultMessageBean.SUCCESS);
        } catch (Exception e) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "任务分配失败");
        }
    }

    /**
     * 获得项目目前的分配情况（修改分配时要显示已分配的情况和未分配的用户信息）
     * key: True 已分配 False 未分配
     *
     * @param projectId
     * @return
     */
    @Override
    public Map<Boolean, List<UserBean>> getUserTaskAllocationInfo(Integer projectId) {
        List<UserBean> allUsers = userService.getAllNormalUsers();
        List<Task> tasks = taskRepository.findAllByProjectid(projectId);

        List<UserBean> allocatedUsers = new ArrayList<>();
        List<UserBean> unallocatedUsers = new ArrayList<>();

        for (UserBean user : allUsers) {
            Optional<Task> task = tasks.stream().filter(t -> t.getUserid().equals(user.userId)).findFirst();
            if (!task.isPresent()) {
                unallocatedUsers.add(user);
            } else {
                Task t = task.get();
                TaskAllocationUserBean taskAllocationUserBean = new TaskAllocationUserBean(user.userId, user.username, t.getBegin(), t.getEnd());
                allocatedUsers.add(taskAllocationUserBean);
            }
        }

        Map<Boolean, List<UserBean>> res = new HashMap<>();
        res.put(true, allocatedUsers);
        res.put(false, unallocatedUsers);
        return res;
    }
}
