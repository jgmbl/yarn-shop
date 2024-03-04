package pl.jgmbl.yarnshop.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }

    @PostMapping("/login")
    public String submitLoginForm () {
        return "redirect:/account";
    }
}
