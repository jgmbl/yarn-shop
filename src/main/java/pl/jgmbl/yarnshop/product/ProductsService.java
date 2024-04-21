package pl.jgmbl.yarnshop.product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.PurchasedYarn;
import pl.jgmbl.yarnshop.PurchasedYarnRepository;
import pl.jgmbl.yarnshop.user.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductsService {
    @Autowired
    private YarnRepository yarnRepository;
    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    HttpSession httpSession;
    @Autowired
    HttpServletRequest httpServletRequest;

    public Iterable<Yarn> getAllYarn() {
        return yarnRepository.findAll();
    }

    public Optional<Yarn> getYarn(Integer id) {
        return yarnRepository.findById(id);
    }

    protected List<String> getUnduplicatedData(Iterable<Yarn> duplicated, Function<Yarn, String> function) {
        ArrayList<String> unduplicatedData = new ArrayList<>();
        for (Yarn yarn : duplicated) {
            String data = function.apply(yarn);

            if (unduplicatedData.contains(data)) {
                continue;
            }

            unduplicatedData.add(data);
        }

        return unduplicatedData;
    }


    protected Purchase returnCurrentPurchase(User loggedUser) {
        Date date = new Date();
        String state = "Added to cart";

        return new Purchase(loggedUser, date, state);
    }

    protected PurchasedYarn createPurchasedYarnByYarnId(Integer yarnId, Integer count, Purchase purchase) {
        Optional<Yarn> yarnOptional = yarnRepository.findById(yarnId);
        Yarn yarn = yarnOptional.orElse(null);

        return new PurchasedYarn(purchase, yarn, count);

    }
}
