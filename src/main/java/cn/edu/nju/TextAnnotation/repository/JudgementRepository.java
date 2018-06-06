package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.bean.JudgementDto;
import cn.edu.nju.TextAnnotation.model.Judgement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JudgementRepository extends JpaRepository<Judgement,Integer> {
    List<Judgement> findAllByStatuteIdAndIsrelated(String statuteid, Integer isrelated);
    List<Judgement> findAllByProjectId(Integer projectId);

    @Query(value = "select new cn.edu.nju.TextAnnotation.bean.JudgementDto(count(j.statuteId),j.statuteId) from Judgement j where j.projectId = :projectid and j.isrelated = :isrelated group by j.statuteId order by count(j.statuteId) desc ")
    List<JudgementDto> findJudgementDto(@Param("projectid") Integer projectid,@Param("isrelated") Integer isrelated);

    Judgement findFirstByUserIdAndFactIdAndStatuteIdAndProjectId(Integer userid,Integer factid,String statuteid,Integer projectid);
}
