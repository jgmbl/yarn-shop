package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jgmbl.yarnshop.Storage;
import pl.jgmbl.yarnshop.StorageRepository;

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
