package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskManagementService {
    public List<Task> findTaskOfUser(int userid);

    public Task findTaskOfUseridAndProjectid(int userid, int projectid);

    public boolean saveTask(Task t);
}