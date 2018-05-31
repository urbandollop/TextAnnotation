package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Statute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatuteRepository extends JpaRepository<Statute,String> {
   Statute findByStatuteid(String statuteid);
   Statute findFirstByName(String name);

   @Override
   List<Statute> findAll();
}
