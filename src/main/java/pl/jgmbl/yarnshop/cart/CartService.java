package pl.jgmbl.yarnshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.PurchasedYarn;
import pl.jgmbl.yarnshop.PurchasedYarnRepository;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;

    protected List<List<Object>> getCardData(String username) {
        List<PurchasedYarn> purchasedYarn = getPurchasedYarn(username);
        List<List<Object>> cartData = new ArrayList<>();

        for (PurchasedYarn purchase : purchasedYarn) {
            List<Object> onePurchase = new ArrayList<>();

            onePurchase.add(purchase.getYarn().getName());
            onePurchase.add(purchase.getCount());
            onePurchase.add(purchase.getYarn().getPrice());
            onePurchase.add(multiplyIntegerBigDecimal(purchase.getCount(), purchase.getYarn().getPrice()));

            cartData.add(onePurchase);
        }

        return cartData;
    }

    protected BigDecimal totalPrice(List<List<Object>> allDataFromCard) {
        BigDecimal sum = BigDecimal.ZERO;

        for (List<Object> record : allDataFromCard) {
            Object totalPriceOfYarn = record.get(3);
            BigDecimal totalPriceOfYarnBigDecimal = (BigDecimal) totalPriceOfYarn;
            sum = sum.add(totalPriceOfYarnBigDecimal);
        }
        return sum;
    }

    protected List<PurchasedYarn> getPurchasedYarn(String username) {
        Purchase lastPurchase = getLastPurchaseAddedToCardByLoggedUser(username);
        List<PurchasedYarn> purchasedYarnList = purchasedYarnRepository.findByPurchase(lastPurchase);

        return purchasedYarnList;
    }

    protected BigDecimal multiplyIntegerBigDecimal(Integer integer, BigDecimal bigDecimal) {

        return bigDecimal.multiply(BigDecimal.valueOf(integer));
    }

    private Purchase getLastPurchaseAddedToCardByLoggedUser(String username) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        User user = optionalUser.orElse(null);

        List<Purchase> addedToCart = purchaseRepository.findByUserAndState(user, "Added to cart");
        Purchase lastPurchase = addedToCart.getLast();

        return lastPurchase;
    }
}
