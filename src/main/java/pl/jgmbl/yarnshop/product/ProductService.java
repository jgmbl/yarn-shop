package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {
    @Autowired
    private YarnRepository yarnRepository;

    public Optional<Yarn> getYarn(Integer id) {
        return yarnRepository.findById(id);
    }
}
