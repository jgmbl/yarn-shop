package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchasedYarn;
import pl.jgmbl.yarnshop.PurchasedYarnRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductsService {
    @Autowired
    YarnRepository yarnRepository;
    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;

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

    protected Optional<PurchasedYarn> updatePurchasedYarn(Integer yarnId) {
        PurchasedYarn updatedPurchasedYarn = getUpdatedPurchasedYarn(yarnId);
        List<PurchasedYarn> byYarnId = purchasedYarnRepository.findByYarnId(yarnId);
        PurchasedYarn last = byYarnId.getLast();

        return Optional.ofNullable(last)
                .map(purchasedyarn -> {
                    if (purchasedyarn.getPurchase() != null)
                        purchasedyarn.setPurchase(updatedPurchasedYarn.getPurchase());
                    if (purchasedyarn.getYarn() != null) purchasedyarn.setYarn(updatedPurchasedYarn.getYarn());
                    if (purchasedyarn.getCount() != null) purchasedyarn.setCount(updatedPurchasedYarn.getCount());

                    return purchasedYarnRepository.save(purchasedyarn);
                });
    }

    private PurchasedYarn getUpdatedPurchasedYarn(int yarnId) {
        List<PurchasedYarn> byYarnId = purchasedYarnRepository.findByYarnId(yarnId);
        List<PurchasedYarn> onlyAddedToCart = new ArrayList<>();

        for (PurchasedYarn purchasedYarn1 : byYarnId) {
            if (purchasedYarn1.getPurchase().getState().equals("Added to cart")) {
                onlyAddedToCart.add(purchasedYarn1);
            }
        }

        int count = 0;
        for (PurchasedYarn addedToCart : onlyAddedToCart) {
            count += addedToCart.getCount();
        }

        PurchasedYarn purchasedYarn = new PurchasedYarn();
        Purchase purchase = onlyAddedToCart.getFirst().getPurchase();
        Yarn yarn = onlyAddedToCart.getFirst().getYarn();
        purchasedYarn.setPurchase(purchase);
        purchasedYarn.setYarn(yarn);
        purchasedYarn.setCount(count);

        return purchasedYarn;
    }
}
