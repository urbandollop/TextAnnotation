package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.bean.NewUserBean;
import cn.edu.nju.TextAnnotation.bean.ResultMessageBean;
import cn.edu.nju.TextAnnotation.bean.UserBean;
import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = (User) auth.getPrincipal();
            Optional<User> opt = userRepository.findById(user.getUser_id());
            if (opt.isPresent()) {
                return opt.get();
            }
        }
        return null;
    }

    /**
     * 获得所有普通用户
     *
     * @return
     */
    @Override
    public List<UserBean> getAllNormalUsers() {
        List<User> users = userRepository.findUsersByRole(0);
        List<UserBean> userBeans = new ArrayList<>();
        users.forEach(user -> userBeans.add(new UserBean(user)));
        return userBeans;
    }

    /**
     * 用户注册
     *
     * @param newUserBean
     * @return
     */
    @Override
    public ResultMessageBean signUp(NewUserBean newUserBean) {
        User user = userRepository.findUserByNameAndRole(newUserBean.username, 0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (user != null) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "用户名已被注册!");
        } else {
            User newUser = new User(newUserBean.username, encoder.encode(newUserBean.password), 0);
            userRepository.save(newUser);
            return new ResultMessageBean(ResultMessageBean.SUCCESS, "注册成功!");
        }
    }

    @Override
    public ResultMessageBean modify(Integer id, String passwd) {
        User user = userRepository.findUserByUseridAndRole(id, 0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user == null) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "该用户不存在！");
        } else {
            user.setPassword(encoder.encode(passwd));
            userRepository.save(user);
            return new ResultMessageBean(ResultMessageBean.SUCCESS, "密码修改成功！");
        }
    }

    @Override
    public ResultMessageBean modifySelf(Integer id, String oldpass, String newpass) {
        if (oldpass == null || oldpass.length() == 0 || newpass == null || newpass.length() == 0) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "密码不能为空！");
        }
        User user = userRepository.findUserByUseridAndRole(id, 0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user == null || !encoder.matches(oldpass, user.getPassword())) {
            return new ResultMessageBean(ResultMessageBean.ERROR, "旧密码输入错误！");
        }
        user.setPassword(encoder.encode(newpass));
        userRepository.save(user);
        return new ResultMessageBean(ResultMessageBean.SUCCESS, "密码修改成功！");
    }
}
