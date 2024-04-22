package pl.jgmbl.yarnshop.product;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jgmbl.yarnshop.*;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private YarnRepository yarnRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/products")
    public String displayProductsPage(Model model) {
        Iterable<Yarn> allYarn = productsService.getAllYarn();

        model.addAttribute("allYarn", allYarn);

        return "productspage";
    }


    @GetMapping("/products/{id}")
    public String displayProductPage(@PathVariable Integer id, Model model) {
        Optional<Yarn> yarnByIdOptional = productsService.getYarn(id);
        Yarn yarn = yarnByIdOptional.orElse(null);
        Optional<Storage> storageIdOptional = storageRepository.findByYarnId(id);
        Storage storage = storageIdOptional.orElse(null);

        if (yarn == null) {
            return "redirect:/products";
        }

        model.addAttribute("yarnById", yarn);
        model.addAttribute("storage", storage);

        return "productpage";
    }

    @PostMapping("/cart")
    public String purchaseYarn(@ModelAttribute("numberOfSkeinsForm") NumberOfSkeinsForm numberOfSkeinsForm, @RequestParam("productId") Integer productId, HttpSession httpSession) {
        Integer quantity = numberOfSkeinsForm.getQuantity();

        if (httpSession.getAttribute("username") != null) {
            Optional<User> optionalUser = userRepository.findByEmail((String) httpSession.getAttribute("username"));
            User user = optionalUser.orElse(null);

            List<Purchase> purchases = purchaseRepository.findByUserAndState(user, "Added to cart");
            if (purchases.isEmpty()) {
                Purchase currentPurchase = productsService.returnCurrentPurchase(user);
                purchaseRepository.save(currentPurchase);

                PurchasedYarn purchasedYarnByYarnId = productsService.createPurchasedYarnByYarnId(productId, quantity, currentPurchase);
                purchasedYarnRepository.save(purchasedYarnByYarnId);
            } else {
                Purchase addedToCart = purchases.get(0);
                PurchasedYarn purchasedYarnByYarnId = productsService.createPurchasedYarnByYarnId(productId, quantity, addedToCart);
                purchasedYarnRepository.save(purchasedYarnByYarnId);
            }

        } else {
            return "redirect:/login";
        }

        return "redirect:/cart";
    }

    @GetMapping("/products/compositions")
    public String displayProductsCompostionsPage(Model model) {
        Iterable<Yarn> allYarn = productsService.getAllYarn();
        List<String> unduplicatedCompositions = productsService.getUnduplicatedData(allYarn, Yarn::getComposition);

        model.addAttribute("allYarn", unduplicatedCompositions);

        return "productscompositionspage";
    }

    @GetMapping("/products/compositions/{composition}")
    public String displayProductsCompostionPage(@PathVariable String composition, Model model) {
        composition = composition.substring(composition.lastIndexOf(" ") + 1);
        List<Yarn> byComposition = yarnRepository.findByComposition("100% " + composition);

        model.addAttribute("yarnByComposition", byComposition);
        model.addAttribute("composition", composition);

        if (byComposition.isEmpty()) {
            return "redirect:/products/compositions";
        }

        return "productcompositionpage";
    }

    @GetMapping("/products/producers")
    public String displayProductsProducerPage(Model model) {
        Iterable<Yarn> allYarn = productsService.getAllYarn();
        List<String> unduplicatedProducers = productsService.getUnduplicatedData(allYarn, Yarn::getProducer);

        for (String producer : unduplicatedProducers) {
            System.out.println(producer);
        }

        model.addAttribute("allProducers", unduplicatedProducers);

        return "productsproducerspage";
    }

    @GetMapping("/products/producers/{producer}")
    public String displayProductsProducerPage(@PathVariable String producer, Model model) {
        List<Yarn> byProducer = yarnRepository.findByProducer(producer);

        model.addAttribute("yarnByProducer", byProducer);
        model.addAttribute("producer", producer);

        if (byProducer.isEmpty()) {
            return "redirect:/products/producers";
        }

        return "productsproducerpage";
    }
}
