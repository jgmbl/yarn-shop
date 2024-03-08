package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComparePasswords comparePasswords;

    boolean isFormNotBlank (String property, String property1, String property2) {
        return property != null && property1 != null && property2 != null;
    }

    boolean doesUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String registerUser(RegisterForm registerForm, Model model) {

        String email = registerForm.getEmail();
        String password = registerForm.getPassword();
        String confirmedPassword = registerForm.getConfirmpassword();

        if (isFormNotBlank(email, password, confirmedPassword) && comparePasswords.comparePasswords(password, confirmedPassword)
            && !doesUserExist(email)) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
