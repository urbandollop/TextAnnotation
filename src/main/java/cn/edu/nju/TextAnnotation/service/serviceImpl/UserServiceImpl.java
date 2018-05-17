package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.model.User;
import cn.edu.nju.TextAnnotation.repository.UserRepository;
import cn.edu.nju.TextAnnotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
}
