package cn.edu.nju.TextAnnotation.bean;

import java.util.Objects;

/**
 * @author keenan on 2018/5/31
 */
public class ResultMessageBean {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String resultMessage;
    private String resultCode;

    public ResultMessageBean(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ResultMessageBean(String resultCode) {
        this.resultCode = resultCode;
        this.resultMessage = "";
    }

    public ResultMessageBean() {
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultMessageBean)) return false;
        ResultMessageBean that = (ResultMessageBean) o;
        return Objects.equals(resultCode, that.resultCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode);
    }
}
