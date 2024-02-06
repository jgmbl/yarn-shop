package pl.jgmbl.yarnshop;

import org.springframework.web.bind.annotation.GetMapping;

public class RegisterController {
    @GetMapping("/register")
    public String displayRegisterPage() {
        return "register2";
    }
}
