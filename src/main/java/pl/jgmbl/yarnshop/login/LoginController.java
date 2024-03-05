package pl.jgmbl.yarnshop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

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
        Optional<User> optionalUser = userRepository.findByEmail(loginForm.getEmail());

        if (loginService.checkIfUserExists(loginForm.getEmail())) {
            User user = optionalUser.get();

            String submittedLoginForm = loginService.submitLoginForm(loginForm, user, model);

            return submittedLoginForm;
        } else {
            model.addAttribute("InvalidCredentials", true);
            return "redirect:/login";
        }
    }

}
