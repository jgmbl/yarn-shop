package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.UserRepository;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    boolean comparePasswords (String password1, String password2) {
        return password1.equals(password2);
    }

    boolean isFormNotBlank (String property, String property1, String property2) {
        return property != null && property1 != null && property2 != null;
    }

    public String registerUser(RegisterForm registerForm, Model model) {

        String email = registerForm.getEmail();
        String password = registerForm.getPassword();
        String confirmedPassword = registerForm.getConfirmpassword();

        if (isFormNotBlank(email, password, confirmedPassword) && comparePasswords(password, confirmedPassword)) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
