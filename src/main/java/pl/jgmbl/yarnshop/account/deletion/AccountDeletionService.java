package pl.jgmbl.yarnshop.account.deletion;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class AccountDeletionService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    public Boolean deleteAccountBySessionId() {
        Integer sessionId = returnSessionAccountId();
        if (!userRepository.existsById(sessionId)) {
            return false;
        }

        userRepository.deleteById(sessionId);
        return true;
    }

    public Integer returnSessionAccountId() {
        Object sessionAttribute = httpSession.getAttribute("username");
        String sessionEmail = sessionAttribute.toString();
        Optional<User> optionalUser = userRepository.findByEmail(sessionEmail);

        Integer userId = optionalUser.map(User::getId).orElse(null);

        return userId;
    }
}
