package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Judgement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JudgementRepository extends JpaRepository<Judgement, Integer> {
    List<Judgement> findAllByProjectId(int projectId);
}
