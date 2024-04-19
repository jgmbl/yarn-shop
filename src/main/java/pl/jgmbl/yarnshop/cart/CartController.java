package pl.jgmbl.yarnshop.cart;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String displayCartPage(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("username") != null) {
            List<List<Object>> listOfPurchasedYarn = cartService.getCardData((String) httpSession.getAttribute("username"));
            BigDecimal totalPrice = cartService.totalPrice(listOfPurchasedYarn);

            model.addAttribute("purchaseList", listOfPurchasedYarn);
            model.addAttribute("totalPrice", totalPrice);
            return "cartpage";

        }

        return "redirect:/login";
    }
}
