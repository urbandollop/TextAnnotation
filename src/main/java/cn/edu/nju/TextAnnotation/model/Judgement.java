package cn.edu.nju.TextAnnotation.model;

import javax.persistence.*;

@Entity
@Table(name="judgement")
public class Judgement {
//    judgement_id	int，自增	id
//    user_id	int	用户id
//    fact_id	int	事实id
//    statute_id	varchar	法条id
//    isrelated	int	是否相关，0不相关，1相关

    @Id
    @Column(name="judgement_id")
    @GeneratedValue
    private Integer judgementid;
    @Column(name="user_id")
    private Integer userid;
    @Column(name="fact_id")
    private Integer factid;
    @Column(name="statute_id")
    private String statuteid;
    @Column(name="isrelated")
    private Integer isrelated;

    public Judgement(){}

    public Integer getJudgementid() {
        return judgementid;
    }

    public void setJudgementid(Integer judgementid) {
        this.judgementid = judgementid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
}
