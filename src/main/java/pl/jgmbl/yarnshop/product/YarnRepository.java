package pl.jgmbl.yarnshop.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YarnRepository extends JpaRepository<Yarn, Integer> {
    List<Yarn> findByComposition(String composition);
    List<Yarn> findByProducer(String producer);
}
