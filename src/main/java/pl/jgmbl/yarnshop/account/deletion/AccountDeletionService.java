package pl.jgmbl.yarnshop.account.deletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class AccountDeletionService {
    @Autowired
    UserRepository userRepository;

    private String email;

    public AccountDeletionService() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean deleteAccountBySessionId() {
        Integer sessionId = returnSessionAccountId();
        if (!userRepository.existsById(sessionId)) {
            return false;
        }

        userRepository.deleteById(sessionId);
        return true;
    }

    public Integer returnSessionAccountId() {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        Integer userId = optionalUser.map(User::getId).orElse(null);

        return userId;
    }
}
