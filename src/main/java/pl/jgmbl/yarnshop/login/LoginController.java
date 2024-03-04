package pl.jgmbl.yarnshop.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jgmbl.yarnshop.user.User;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }


    @PostMapping("/login")
    public String submitLoginForm (@ModelAttribute(name = "loginForm") User user, Model model) {
        if (user.getEmail().equals("admin@test.com") && user.getPassword().equals("password")) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "redirect:/login";
    }
}
