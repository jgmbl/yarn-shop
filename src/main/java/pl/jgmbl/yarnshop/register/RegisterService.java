package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

@Service
public class RegisterService {
    private final FormValidator formValidator;

    @Autowired
    public RegisterService(FormValidator formValidator) {
        this.formValidator = formValidator;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPasswordService hashPasswordService;

    public String registerUser(RegisterForm registerForm, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {

        PasswordValidator lengthPasswordValidator = new LengthPasswordValidator();
        PasswordValidator specialCharactersValidator = new SpecialCharactersValidator();

        if (formValidator.validator(registerForm.getEmail(), registerForm.getPassword(), registerForm.getConfirmpassword()) &&
            lengthPasswordValidator.validate(registerForm.getPassword()) && specialCharactersValidator.validate(registerForm.getPassword())) {

            byte[] hashedPassword = hashPasswordService.hashPassword(registerForm.getPassword());

            User user = new User(registerForm.getEmail().toLowerCase(), hashedPassword);
            userRepository.save(user);

            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "register2";
    }
}
