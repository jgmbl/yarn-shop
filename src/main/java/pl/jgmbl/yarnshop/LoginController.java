package pl.jgmbl.yarnshop;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    @GetMapping("/login")
    public static String displayLoggingPage() {
        return "login";
    }
}
