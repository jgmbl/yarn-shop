package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jgmbl.yarnshop.user.UserRepository;

@Component
public class EmailValidator {

    @Autowired
    UserRepository userRepository;

    boolean doesUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
