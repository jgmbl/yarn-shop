package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.UserRepository;

@Service
public class RegisterService {
    @Autowired
    FormValidator formValidator;

    public String registerUser(RegisterForm registerForm, Model model) {
        if (formValidator.validator(registerForm.getEmail(), registerForm.getPassword(), registerForm.getConfirmpassword())) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
