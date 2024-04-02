package pl.jgmbl.yarnshop.account;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.Purchase;
import pl.jgmbl.yarnshop.PurchaseRepository;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    PurchaseRepository purchaseRepository;

    public Optional<Purchase> findPurchasesByAccountId (Integer accountId) {
        return purchaseRepository.findById(accountId);
    }

    public Integer returnSessionAccountId(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        Integer userId = optionalUser.map(User::getId).orElse(null);

        return userId;
    }
}
