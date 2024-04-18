package pl.jgmbl.yarnshop.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
    
    @GetMapping("/search")
    public String displaySearchPage() {
        return "search";
    }
}
