package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.Project;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProjectVO {
    private Integer project_id;
    private String name;
    private String starttime;
    private Boolean allocated;

    public ProjectVO(Integer project_id, String name, LocalDateTime starttime, Boolean allocated) {
        this.project_id = project_id;
        this.name = name;
        this.setStarttime(starttime);
        this.allocated = allocated;
    }

    public ProjectVO() {
    }


    public static ProjectVO fromProject(Project project) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setName(project.getName());
        projectVO.setProject_id(project.getProject_id());
        projectVO.setStarttime(project.getStarttime());
        projectVO.setAllocated(project.getAllocated());
        return projectVO;
    }

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.starttime = df.format(starttime);
    }

    public Boolean getAllocated() {
        return allocated;
    }

    public void setAllocated(Boolean allocated) {
        this.allocated = allocated;
    }
}