package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public String displayAccountPage() {
        if (httpSession.getAttribute("username") != null) {
            return "account2";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logOutUser() {
        if (httpSession.getAttribute("username") != null) {
            accountService.endSession();
            return "redirect:/login";
        }

        return "account2";
    }
}
