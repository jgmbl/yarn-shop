package pl.jgmbl.yarnshop.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser () {
        String id = httpSession.getId();
        Integer sessionId = Integer.parseInt(id);

        if (!userRepository.existsById(sessionId)) {
            return false;
        }

        userRepository.deleteById(sessionId);

        return true;
    }
}
