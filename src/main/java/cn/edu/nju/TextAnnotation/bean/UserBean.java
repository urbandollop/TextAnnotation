package cn.edu.nju.TextAnnotation.bean;

import cn.edu.nju.TextAnnotation.model.User;

/**
 * @author keenan on 2018/5/31
 */
public class UserBean {
    public Integer userId;
    public String username;

    public UserBean() {
    }

    public UserBean(User user) {
        this.userId=user.getUser_id();
        this.username=user.getName();
    }

    public UserBean(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
