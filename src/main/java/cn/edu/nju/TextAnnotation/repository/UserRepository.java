package cn.edu.nju.TextAnnotation.repository;

import cn.edu.nju.TextAnnotation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author keenan on 2018/5/16
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByName(String name);

    List<User> findUsersByRole(Integer role);

    User findUserByNameAndRole(String name, Integer role);
}
