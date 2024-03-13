package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jgmbl.yarnshop.login.LoginForm;

@Controller
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @GetMapping("/resetpassword")
    public String displayResetPasswordPage(@RequestParam(value = "reset", required = false) String reset, Model model) {
        if ("true".equals(reset)) {
            return "resetpassword_passwords";
        } else {
            return "resetpassword_email";
        }
    }

    @PostMapping("/login/resetpassword")
    public String confirmEmail(@ModelAttribute(name = "emailForm") EmailForm emailForm, Model model) {
        if (resetPasswordService.doesUserExist(emailForm.getEmail())) {
            return "redirect:/resetpassword?reset=true";
        }

        model.addAttribute("InvalidCredentials", true);
        return "resetpassword_email";
    }
}
