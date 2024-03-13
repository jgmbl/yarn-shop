package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class ResetPasswordService {
    @Autowired
    UserRepository userRepository;

    public boolean doesUserExist (String email) {
        Optional<User> findByEmail = userRepository.findByEmail(email.toLowerCase());

        return findByEmail.isPresent();
    }
}
