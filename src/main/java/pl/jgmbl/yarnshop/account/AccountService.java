package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    PurchaseRepository purchaseRepository;

    public ArrayList<String> findPurchasesByAccountId (Integer accountId) {
        ArrayList<String> purchasesByAccountId = new ArrayList<>();
        List<Purchase> allPurchases = findAllPurchases();

        for (Purchase purchase : allPurchases) {
            if (purchase.getUser().getId().equals(accountId)) {
                purchasesByAccountId.add(purchase.toString());
            }
        }

        return purchasesByAccountId;
    }

    public Integer returnSessionAccountId(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        Integer userId = optionalUser.map(User::getId).orElse(null);

        return userId;
    }

    private List<Purchase> findAllPurchases () {
        return purchaseRepository.findAll().stream().toList();
    }

}
