package pl.jgmbl.yarnshop;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jgmbl.yarnshop.user.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserAndState(User user, String state);
}
