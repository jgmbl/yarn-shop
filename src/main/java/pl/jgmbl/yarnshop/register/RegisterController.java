package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String displayRegisterPage() {
        return "register2";
    }
}
