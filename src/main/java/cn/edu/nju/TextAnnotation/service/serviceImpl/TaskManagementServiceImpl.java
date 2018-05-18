package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.model.Task;
import cn.edu.nju.TextAnnotation.repository.TaskRepository;
import cn.edu.nju.TextAnnotation.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findTaskOfUser(int userid) {
        return taskRepository.findAllByUserid(userid);
    }

    @Override
    public Task findTaskOfUseridAndProjectid(int userid,int projectid){return taskRepository.findFirstByUseridAndProjectid(userid,projectid); }
}
