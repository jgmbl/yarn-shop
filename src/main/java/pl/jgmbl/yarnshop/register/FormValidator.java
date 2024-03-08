package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jgmbl.yarnshop.user.UserRepository;

@Component
public class FormValidator implements RegisterFormValidator{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean formValidator(String email, String password, String confirmedPassword) {
        return email != null && password != null && confirmedPassword != null;
    }

    @Override
    public boolean formValidator(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    @Override
    public boolean formValidator(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean validator (String email, String password, String confirmedPassword) {
        return formValidator(email) && formValidator(password, confirmedPassword) && formValidator(email, password, confirmedPassword);
    }
}
