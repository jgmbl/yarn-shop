package pl.jgmbl.yarnshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedYarnRepository extends JpaRepository<PurchasedYarn, Integer> {
    List<PurchasedYarn> findByPurchase(Purchase purchase);
    List<PurchasedYarn> findByYarnId (Integer yarnId);
}
