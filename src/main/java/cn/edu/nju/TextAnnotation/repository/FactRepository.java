package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactRepository extends JpaRepository<Fact,Integer> {
    List<Fact> findByInstrumentid(String instrumnetid);

    Fact findFirstByInstrumentidAndNum(String iid,Integer num);
}
