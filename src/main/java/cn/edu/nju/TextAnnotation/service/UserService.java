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
     *
     * @return
     */
    List<UserBean> getAllNormalUsers();

    /**
     * 用户注册
     *
     * @param newUserBean
     * @return ResultMessageBean.ERROR, "用户名已被注册!"
     * ResultMessageBean.SUCCESS,"注册成功!"
     */
    ResultMessageBean signUp(NewUserBean newUserBean);

    /**
     * 修改用户密码
     *
     * @param id
     * @param passwd
     * @return ResultMessageBean.ERROR, "修改失败!"
     * ResultMessageBean.SUCCESS,"修改成功!"
     */
    ResultMessageBean modify(Integer id, String passwd);
}
