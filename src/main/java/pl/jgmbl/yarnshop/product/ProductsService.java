package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private YarnRepository yarnRepository;

    public Optional<Yarn> getYarn(Integer id) {
        return yarnRepository.findById(id);
    }
}
