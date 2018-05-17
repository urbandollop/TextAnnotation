package cn.edu.nju.TextAnnotation.security;

import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author keenan on 2018/5/16
 */
public class SystemUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

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
}
