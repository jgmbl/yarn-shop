package pl.jgmbl.yarnshop.cart;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.PurchasedYarn;
import pl.jgmbl.yarnshop.PurchasedYarnRepository;
import pl.jgmbl.yarnshop.product.NumberOfSkeinsForm;
import pl.jgmbl.yarnshop.product.YarnRepository;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    YarnRepository yarnRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;

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

    @PostMapping("/cart")
    public String purchaseYarn(@ModelAttribute("numberOfSkeinsForm") NumberOfSkeinsForm numberOfSkeinsForm, @RequestParam("productId") Integer productId, HttpSession httpSession) {
        Integer quantity = numberOfSkeinsForm.getQuantity();

        if (httpSession.getAttribute("username") != null) {
            Optional<User> optionalUser = userRepository.findByEmail((String) httpSession.getAttribute("username"));
            User user = optionalUser.orElse(null);

            List<Purchase> purchases = purchaseRepository.findByUserAndState(user, "Added to cart");
            if (purchases.isEmpty()) {
                Purchase currentPurchase = cartService.returnCurrentPurchase(user);
                purchaseRepository.save(currentPurchase);

                PurchasedYarn purchasedYarnByYarnId = cartService.createPurchasedYarnByYarnId(productId, quantity, currentPurchase);
                purchasedYarnRepository.save(purchasedYarnByYarnId);
            } else {
                Purchase addedToCart = purchases.get(0);
                PurchasedYarn purchasedYarnByYarnId = cartService.createPurchasedYarnByYarnId(productId, quantity, addedToCart);
                purchasedYarnRepository.save(purchasedYarnByYarnId);
            }

        } else {
            return "redirect:/login";
        }

        return "redirect:/cart";
    }

}
