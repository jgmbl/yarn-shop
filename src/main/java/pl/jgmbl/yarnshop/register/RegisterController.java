package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String displayRegisterPage() {
        return "register2";
    }

    @PostMapping("/register")
    public String registerUser() {
        return "account2";
    }
}
