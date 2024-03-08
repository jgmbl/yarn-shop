package pl.jgmbl.yarnshop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jgmbl.yarnshop.user.UserRepository;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }


    @PostMapping("/login")
    public String submitLoginForm (@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
        return loginService.logInUser(loginForm, model);
    }
}
