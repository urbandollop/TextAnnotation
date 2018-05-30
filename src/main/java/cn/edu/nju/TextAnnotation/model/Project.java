package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
public class Project implements Serializable{
    @Id
    @GeneratedValue
    private Integer project_id;
    @Column
    private String name;
    @Column
    private LocalDateTime starttime;
    @Column
    /**
     * 是否已进行任务分配
     * 已分配则为True
     */
    private Boolean allocated;

    public Project(){}

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public Boolean getAllocated() {
        return allocated;
    }

    public void setAllocated(Boolean allocated) {
        this.allocated = allocated;
    }
}
