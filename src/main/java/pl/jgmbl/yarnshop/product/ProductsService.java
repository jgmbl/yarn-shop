package pl.jgmbl.yarnshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    @Autowired
    private YarnRepository yarnRepository;

    public Iterable<Yarn> getAllYarn() {
        return yarnRepository.findAll();
    }

    public Optional<Yarn> getYarn(Integer id) {
        return yarnRepository.findById(id);
    }

    public List<String> getUnduplicatedComposition(Iterable<Yarn> duplicatedComposition) {
        ArrayList<String> unduplicatedCompositions = new ArrayList<>();
        for (Yarn yarn : duplicatedComposition) {
            String composition = yarn.getComposition();

            if (unduplicatedCompositions.contains(composition)) {
                continue;
            }

            unduplicatedCompositions.add(composition);
        }

        return unduplicatedCompositions;
    }
}
