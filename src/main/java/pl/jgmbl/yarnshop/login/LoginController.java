package pl.jgmbl.yarnshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String displayLoggingPage() {
        return "login2";
    }
}
