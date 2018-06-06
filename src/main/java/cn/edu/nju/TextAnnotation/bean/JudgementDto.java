package cn.edu.nju.TextAnnotation.bean;

public class JudgementDto {
    private Long count;
    private String statuteid;

    public JudgementDto() {
    }
    public JudgementDto(long count,String statuteid){
        this.count=count;
        this.statuteid=statuteid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getStatuteid() {
        return statuteid;
    }

    public void setStatuteid(String statuteid) {
        this.statuteid = statuteid;
    }
}
