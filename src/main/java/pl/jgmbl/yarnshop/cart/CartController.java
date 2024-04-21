package pl.jgmbl.yarnshop.cart;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.product.YarnRepository;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    YarnRepository yarnRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

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
