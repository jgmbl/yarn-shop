package pl.jgmbl.yarnshop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jgmbl.yarnshop.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }


    @PostMapping("/login")
    public String logInUser(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return loginService.logInUser(loginForm, model);
    }
}
