package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "judgement")
public class Judgement implements Serializable {
    @Id
    @GeneratedValue
    private int judgement_id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "fact_id")
    private int factId;
    @Column(name = "statute_id")
    private String statuteId;
    @Column
    private int isrelated;
    @Column(name = "project_id")
    private int projectId;

    public int getJudgement_id() {
        return judgement_id;
    }

    public void setJudgement_id(int judgement_id) {
        this.judgement_id = judgement_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFactId() {
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

    public int getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(int isrelated) {
        this.isrelated = isrelated;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}