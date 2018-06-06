package cn.edu.nju.TextAnnotation;

import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author keenan on 2018/5/16
 */
public class TestCreateUser extends TextAnnotationApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        String username = "admin";
        String password = "123";
        Integer role = 1;
        User user = new User(username, encodePassword(password), role);
        user.setUser_id(1);
        userRepository.save(user);
//        String username = "user";
//        String password = "user";
//        Integer role = 0;
//        User user = new User(username, encodePassword(password), role);
//        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
