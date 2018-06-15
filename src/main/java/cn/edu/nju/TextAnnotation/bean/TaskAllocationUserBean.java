package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.User;

/**
 * @author keenan on 2018/6/15
 */
public class TaskAllocationUserBean extends UserBean {
    public Integer startNo;
    public Integer endNo;

    public TaskAllocationUserBean() {
    }

    public TaskAllocationUserBean(Integer userId, String username, Integer startNo, Integer endNo) {
        super(userId, username);
        this.startNo = startNo;
        this.endNo = endNo;
    }
}
