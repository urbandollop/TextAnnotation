package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InstrumentRepository extends JpaRepository<Instrument, String> {
    //通过num来查询Instrument
    List<Instrument> findInstrumentsByNumBetween(Integer min, Integer max);
}
