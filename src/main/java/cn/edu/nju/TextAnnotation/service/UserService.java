package cn.edu.nju.TextAnnotation.service;

import cn.edu.nju.TextAnnotation.bean.NewUserBean;
import cn.edu.nju.TextAnnotation.bean.ResultMessageBean;
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

    /**
     * 用户注册
     *
     * @param newUserBean
     * @return
     * ResultMessageBean.ERROR, "用户名已被注册!"
     * ResultMessageBean.SUCCESS,"注册成功!"
     */
    ResultMessageBean signUp(NewUserBean newUserBean);
}
