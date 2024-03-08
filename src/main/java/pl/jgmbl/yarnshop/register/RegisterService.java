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

    public String registerUser(RegisterForm registerForm, Model model) {

        System.out.println(registerForm.getEmail() + " " + registerForm.getPassword() +
                " " + registerForm.getConfirmpassword());

        if (registerForm.getEmail() != null && registerForm.getPassword()
                != null && registerForm.getConfirmpassword() != null) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
