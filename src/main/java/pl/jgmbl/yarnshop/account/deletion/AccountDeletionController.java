package pl.jgmbl.yarnshop.account.deletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDeletionController {

    @Autowired
    AccountDeletionService accountDeletionService;

    @GetMapping("/accountdeletion")
    public String displayDeletionPage() {
        return "account2_confirm_deletion";
    }

    @DeleteMapping("/accountdeletion")
    public String deleteAccount() {
        Boolean isAccountDeleted = accountDeletionService.deleteAccountBySessionId();
        System.out.println(isAccountDeleted.toString());
        if (!isAccountDeleted) {
            return "account2_confirm_deletion";
        }

        return "redirect:/login";
    }
}
