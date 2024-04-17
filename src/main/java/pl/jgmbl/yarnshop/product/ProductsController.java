package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public String displayProductsPage() {
        return "productspage";
    }


    @GetMapping("/products/{id}")
    public String displayProductPage(@PathVariable Integer id, Model model) {
        Optional<Yarn> yarnByIdOptional = productsService.getYarn(id);
        Yarn yarn = yarnByIdOptional.orElse(null);

        model.addAttribute("yarnById", yarn);

        return "productpage";
    }
}