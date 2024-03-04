package pl.jgmbl.yarnshop.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }


    @PostMapping("/login")
    public String submitLoginForm (@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
        if (loginForm.getEmail().equals("admin@test.com") && loginForm.getPassword().equals("password")) {
            return "redirect:/account";
        }

        model.addAttribute("InvalidCredentials", true);
        return "redirect:/login";
    }
}
