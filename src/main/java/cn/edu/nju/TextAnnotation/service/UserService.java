package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.UserBean;
import cn.edu.nju.TextAnnotation.model.User;

import java.util.List;

public interface UserService {
    public User getCurrentUser();

    /**
     * 获得所有普通用户
     * @return
     */
    List<UserBean> getAllNormalUsers();
}
