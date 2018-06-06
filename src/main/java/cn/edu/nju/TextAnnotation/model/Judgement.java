package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "judgement")
public class Judgement implements Serializable {
    @Id
    @GeneratedValue
    private Integer judgement_id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "fact_id")
    private Integer factId;
    @Column(name = "statute_id")
    private String statuteId;
    @Column
    private Integer isrelated;
    @Column(name = "project_id")
    private Integer projectId;

    public Integer getJudgement_id() {
        return judgement_id;
    }

    public void setJudgement_id(int judgement_id) {
        this.judgement_id = judgement_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getFactId() {
        return factId;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public String getStatuteId() {
        return statuteId;
    }

    public void setStatuteId(String statuteId) {
        this.statuteId = statuteId;
    }

    public Integer getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(int isrelated) {
        this.isrelated = isrelated;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}