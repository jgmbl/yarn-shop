package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jgmbl.yarnshop.login.LoginForm;
import pl.jgmbl.yarnshop.user.User;

import java.util.Optional;

@Controller
public class ResetPasswordController {

    String confirmed_email = null;

    @Autowired
    ResetPasswordService resetPasswordService;

    @GetMapping("/login/resetpassword/email")
    public String displayResetPasswordEmailPage() {
        return "resetpassword_email";
    }

    @GetMapping("/login/resetpassword/password")
    public String displayResetPasswordPage() {
        return "resetpassword_passwords";
    }

    @PostMapping("/login/resetpassword/email")
    public String confirmEmail(@ModelAttribute(name = "emailForm") EmailForm emailForm, Model model) {
        if (resetPasswordService.doesUserExist(emailForm.getEmail())) {
            confirmed_email = emailForm.getEmail();
            return "redirect:/login/resetpassword/password";
        }

        model.addAttribute("InvalidCredentials", true);
        return "resetpassword_email";
    }

    @PostMapping("/login/resetpassword/password")
    public String resetPassword(@ModelAttribute(name = "resetForm") ResetForm resetForm, Model model) {
        if (resetPasswordService.checkPasswords(resetForm.getNewpassword(), resetForm.getConfirmpassword())) {
            Optional<User> updatedUser = resetPasswordService.updatePassword(resetForm, confirmed_email, resetForm.getNewpassword());

            if (updatedUser.isPresent()) {
                return "redirect:/login";
            } else {
                model.addAttribute("InvalidCredentials", true);
                return "resetpassword_passwords";
            }
        }

        model.addAttribute("InvalidCredentials", true);
        return "resetpassword_passwords";
    }
}
