package pl.jgmbl.yarnshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.PurchasedYarn;
import pl.jgmbl.yarnshop.PurchasedYarnRepository;
import pl.jgmbl.yarnshop.product.Yarn;
import pl.jgmbl.yarnshop.product.YarnRepository;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
public class CartService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchasedYarnRepository purchasedYarnRepository;
    @Autowired
    YarnRepository yarnRepository;

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

    private Purchase getLastPurchaseAddedToCardByLoggedUser(String username) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        User user = optionalUser.orElse(null);

        List<Purchase> addedToCart = purchaseRepository.findByUserAndState(user, "Added to cart");
        Purchase lastPurchase = addedToCart.getLast();

        return lastPurchase;
    }

    protected List<PurchasedYarn> updateYarn() {
        List<PurchasedYarn> allDataFromPurchasedYarn = purchasedYarnRepository.findAll();
        List<Integer> listOfYarnId = new ArrayList<>();
        List<List<PurchasedYarn>> listsOfPurchasesByYarnId = new ArrayList<>();

        for (PurchasedYarn purchasedYarn : allDataFromPurchasedYarn) {
            if (purchasedYarn.getPurchase().getState().equals("Added to cart") && !listOfYarnId.contains(purchasedYarn.getYarn().getId())) {
                listOfYarnId.add(purchasedYarn.getYarn().getId());
            }
        }

        for (Integer yarnId : listOfYarnId) {
            List<PurchasedYarn> purchasesByYarnId = new ArrayList<>();
            for (PurchasedYarn purchasedYarn : allDataFromPurchasedYarn) {
                if (purchasedYarn.getYarn().getId().equals(yarnId) && purchasedYarn.getPurchase().getState().equals("Added to cart")) {
                    purchasesByYarnId.add(purchasedYarn);
                }
            }
            listsOfPurchasesByYarnId.add(purchasesByYarnId);
        }

        List<List<Integer>> yarnIdAndCount = new ArrayList<>();
        for (List<PurchasedYarn> purchasedYarnList : listsOfPurchasesByYarnId) {
            int totalYarnCount = 0;
            Integer yarnId = null;
            for (PurchasedYarn purchase : purchasedYarnList) {
                if (purchase.getPurchase().getState().equals("Added to cart")) {
                    if (yarnId == null) {
                        yarnId = purchase.getYarn().getId();
                    }
                    totalYarnCount += purchase.getCount();
                }
            }
            if (yarnId != null) {
                List<Integer> yarnIdCount = new ArrayList<>();
                yarnIdCount.add(yarnId);
                yarnIdCount.add(totalYarnCount);
                yarnIdAndCount.add(yarnIdCount);
            }
        }

        List<PurchasedYarn> unduplicatedData = new ArrayList<>();
        for (List<Integer> yarnIdAndCount1 : yarnIdAndCount) {
            Integer yarnId = yarnIdAndCount1.get(0);
            Integer count = yarnIdAndCount1.get(1);
            List<PurchasedYarn> byYarnId = purchasedYarnRepository.findByYarnId(yarnId);
            PurchasedYarn first = byYarnId.getFirst();
            first.setCount(count);

            unduplicatedData.add(first);
        }


        return unduplicatedData;
    }
}
