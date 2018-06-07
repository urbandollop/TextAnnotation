package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.Law1Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Law1ArticleRepository extends JpaRepository<Law1Article,String> {
    Law1Article findFirstByArticleSeqEqualsAndDocNameEquals(String articleSeq,String docName);
}
