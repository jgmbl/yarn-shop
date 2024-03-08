package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.UserRepository;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComparePasswords comparePasswords;

    @Autowired
    BlankFormValidator blankFormValidator;

    @Autowired
    EmailValidator emailValidator;
    

    public String registerUser(RegisterForm registerForm, Model model) {

        String email = registerForm.getEmail();
        String password = registerForm.getPassword();
        String confirmedPassword = registerForm.getConfirmpassword();

        if (blankFormValidator.isFormNotBlank(email, password, confirmedPassword) && comparePasswords.comparePasswords(password, confirmedPassword)
            && !emailValidator.doesUserExist(email)) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
