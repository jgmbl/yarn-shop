package pl.jgmbl.yarnshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @GetMapping("/")
    public static String displayHomepage() {
        return "homepage";
    }
}
