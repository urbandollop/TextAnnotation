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

    public ProjectVO(Integer project_id, String name, LocalDateTime starttime) {
        this.project_id = project_id;
        this.name = name;
        this.setStarttime(starttime);
    }

    public ProjectVO() {
    }


    public static ProjectVO fromProject(Project project) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setName(project.getName());
        projectVO.setProject_id(project.getProject_id());
        projectVO.setStarttime(project.getStarttime());
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
}
