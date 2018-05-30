package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Override
    Optional<Project> findById(Integer id);

    @Override
    List<Project> findAll();
}
