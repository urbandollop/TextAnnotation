package cn.edu.nju.TextAnnotation.bean;

public class JudgeResultBean {
    private int userid;
    private String username;
    private boolean isFinished;

    public JudgeResultBean(int userid, String username, boolean isFinished) {
        this.userid = userid;
        this.username = username;
        this.isFinished = isFinished;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setFinished(boolean FInished) {
        isFinished = FInished;
    }
}
