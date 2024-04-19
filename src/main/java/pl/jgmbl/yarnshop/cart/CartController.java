package pl.jgmbl.yarnshop.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jgmbl.yarnshop.product.Yarn;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String displayCartPage() {
        return "cartpage";
    }
}
