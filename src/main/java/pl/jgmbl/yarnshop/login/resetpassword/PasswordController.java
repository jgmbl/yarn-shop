package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    @GetMapping("/login/resetpassword")
    public String displayResetPasswordPage(@RequestParam (value = "reset", required = false) String reset) {
        if ("true".equals(reset)) {
            return "resetpassword_passwords";
        } else {
            return "resetpassword_email";
        }
    }
}
