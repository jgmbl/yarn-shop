package pl.jgmbl.yarnshop.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product")
    public String displayProductPage() {
        return "productpage";
    }
}
