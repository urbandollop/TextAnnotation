package cn.edu.nju.TextAnnotation.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "law_1_article")
public class Law1Article implements Serializable{

    @Id
    @Column(name="ID")
    private String id;
    @Column(name="CODE")
    private String code;
    @Column(name="ARTICLE_SHORTNAME")
    private String articleShortname;
    @Column(name="ARTICLE_SEQ")
    private String articleSeq;
    @Column(name="ARTICLE_NUM")
    private Integer artcleNum;
    @Column(name="ARTICLE_SUBNUM")
    private Integer articleSubnum;
    @Column(name="ARTICLE_TEXT")
    private String articleText;
    @Column(name="DOC_NAME")
    private String docName;
    @Column(name="PART_SEQ")
    private String partSeq;
    @Column(name="CHAP_SEQ")
    private String chapSeq;
    @Column(name="SECT_SEQ")
    private String sectSeq;

    public Law1Article(){}

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getArticleShortname() {
        return articleShortname;
    }

    public String getArticleSeq() {
        return articleSeq;
    }

    public Integer getArtcleNum() {
        return artcleNum;
    }

    public Integer getArticleSubnum() {
        return articleSubnum;
    }

    public String getArticleText() {
        return articleText;
    }

    public String getDocName() {
        return docName;
    }

    public String getPartSeq() {
        return partSeq;
    }

    public String getChapSeq() {
        return chapSeq;
    }

    public String getSectSeq() {
        return sectSeq;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setArticleShortname(String articleShortname) {
        this.articleShortname = articleShortname;
    }

    public void setArticleSeq(String articleSeq) {
        this.articleSeq = articleSeq;
    }

    public void setArtcleNum(Integer artcleNum) {
        this.artcleNum = artcleNum;
    }

    public void setArticleSubnum(Integer articleSubnum) {
        this.articleSubnum = articleSubnum;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setPartSeq(String partSeq) {
        this.partSeq = partSeq;
    }

    public void setChapSeq(String chapSeq) {
        this.chapSeq = chapSeq;
    }

    public void setSectSeq(String sectSeq) {
        this.sectSeq = sectSeq;
    }
}
