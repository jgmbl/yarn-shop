package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
