package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentAndStatueRepository extends JpaRepository<InstrumentAndStatute,String> {
    List<InstrumentAndStatute> findByInstrumentid(String instrumentid);
}
