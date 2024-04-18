package pl.jgmbl.yarnshop.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jgmbl.yarnshop.product.Yarn;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public String displaySearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String searchData(SearchForm searchForm, Model model) {
        List<Yarn> searchedDataByQuery = searchService.searchDataByQuery(searchForm);

        if (searchedDataByQuery != null) {
            model.addAttribute("searchedData", searchedDataByQuery);
            return "search";
        }

        model.addAttribute("searchError", "No results found.");
        return "search";
    }
}
