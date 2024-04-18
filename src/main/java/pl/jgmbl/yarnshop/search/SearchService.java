package pl.jgmbl.yarnshop.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.product.Yarn;
import pl.jgmbl.yarnshop.product.YarnRepository;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private YarnRepository yarnRepository;

    protected List<Yarn> searchData(SearchForm searchForm) {
        String query = searchForm.getQuery();
        List<Yarn> allDataFromRepository = yarnRepository.findAll();

        List<Yarn> matchingData = allDataFromRepository.stream()
                .filter(yarn -> matchesQuery(yarn, query))
                .toList();

        if (matchingData.isEmpty()) {
            return null;
        }

        return matchingData;
    }

    private static boolean matchesQuery(Yarn yarn, String query) {
        return yarn.getName().toLowerCase().contains(query.toLowerCase())
                || yarn.getProducer().toLowerCase().contains(query.toLowerCase())
                || yarn.getComposition().toLowerCase().contains(query.toLowerCase());
    }
}
