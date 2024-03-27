package pl.jgmbl.yarnshop.account.deletion;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDeletionController {

    @Autowired
    AccountDeletionService accountDeletionService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/accountdeletion")
    public String displayDeletionPage() {
        return "account2_confirm_deletion";
    }

    @DeleteMapping("/accountdeletion")
    public String deleteAccount() {
        accountDeletionService.setEmail(httpSession.getAttribute("username").toString());
        Boolean isAccountDeleted = accountDeletionService.deleteAccountBySessionId();

        if (!isAccountDeleted) {
            return "account2_confirm_deletion";
        }

        return "redirect:/login";
    }
}
