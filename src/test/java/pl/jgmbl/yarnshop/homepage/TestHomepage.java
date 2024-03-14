package pl.jgmbl.yarnshop.homepage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TestHomepage {
    private static HomepageController homepageController;
    private static MockMvc mockMvc;


    @BeforeAll
    public static void setup() {
        homepageController = new HomepageController();
        mockMvc = MockMvcBuilders.standaloneSetup(homepageController).build();
    }

    @Test
    public void testDisplayHomepage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));
    }
}
