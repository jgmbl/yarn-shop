package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegisterService {
    @Autowired
    FormValidator formValidator;

    public String registerUser(RegisterForm registerForm, PasswordValidator passwordValidator, Model model) {

        PasswordValidator lengthPasswordValidator = new LengthPasswordValidator();
        PasswordValidator specialCharactersValidator = new SpecialCharactersValidator();

        if (formValidator.validator(registerForm.getEmail(), registerForm.getPassword(), registerForm.getConfirmpassword()) &&
            lengthPasswordValidator.validate(registerForm.getPassword()) && specialCharactersValidator.validate(registerForm.getPassword())) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
