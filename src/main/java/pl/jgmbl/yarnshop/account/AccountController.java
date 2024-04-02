package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public String displayAccountPage(Model model) {
        if (isUserLoggedIn()) {
            Integer accountId = accountService.returnSessionAccountId(httpSession.getAttribute("username").toString());

            Optional<Purchase> purchases = accountService.findPurchasesByAccountId(accountId);

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
