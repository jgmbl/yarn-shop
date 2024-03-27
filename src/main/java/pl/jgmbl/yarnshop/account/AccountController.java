package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired
    HttpSession httpSession;

    @GetMapping("/account")
    public String displayAccountPage() {
        if (isUserLoggedIn()) {
            return "account2";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logOutUser() {
        if (isUserLoggedIn()) {
            httpSession.invalidate();
            return "redirect:/login";
        }

        return "account2";
    }


    public boolean isUserLoggedIn() {
        if (httpSession.getAttribute("username") != null) {
            return true;
        }

        return false;
    }
}
