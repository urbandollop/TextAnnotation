package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task")
public class Task implements Serializable{
    @Id
    @GeneratedValue
    private Integer task_id;
    @Column(name = "user_id")
    private Integer userid;
    @Column(name = "project_id")
    private Integer projectid;
    @Column
    private Integer begin;
    @Column
    private Integer end;


    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Integer getProject_id() {
        return projectid;
    }

    public void setProject_id(Integer project_id) {
        this.projectid = project_id;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
