package cn.edu.nju.TextAnnotation.bean;

public class JudgementListBean {
    public Integer judementid;
    public Integer factid;
    public String statuteid;
    public Integer isrelated;
    public Integer projectid;

    public JudgementListBean() {

    }

    public Integer getJudementid() {
        return judementid;
    }

    public void setJudementid(Integer judementid) {
        this.judementid = judementid;
    }


    public Integer getFactid() {
        return factid;
    }

    public void setFactid(Integer factid) {
        this.factid = factid;
    }

    public String getStatuteid() {
        return statuteid;
    }

    public void setStatuteid(String statuteid) {
        this.statuteid = statuteid;
    }

    public Integer getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(Integer isrelated) {
        this.isrelated = isrelated;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }
}
