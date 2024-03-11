package pl.jgmbl.yarnshop.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String displayRegisterPage() {
        return "register2";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute(name = "registerForm") RegisterForm registerForm, PasswordValidator passwordValidator, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return registerService.registerUser(registerForm, model);
    }
}
