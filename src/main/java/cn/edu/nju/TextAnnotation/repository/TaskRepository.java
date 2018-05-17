package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAllByUserid(Integer userid);
}
