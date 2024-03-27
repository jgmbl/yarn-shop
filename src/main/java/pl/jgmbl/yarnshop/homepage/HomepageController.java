package pl.jgmbl.yarnshop.homepage;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @Autowired
    HttpSession httpSession;


    @GetMapping("/")
    public String displayHomepage(Model model) {
        isUserLoggedIn(model);

        return "homepage";
    }


    public void isUserLoggedIn (Model model) {
        Object user = httpSession.getAttribute("username");

        if (user != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
    }
}
