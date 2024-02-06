package pl.jgmbl.yarnshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TestRegisterController {
    private static RegisterController registerController;
    private static MockMvc mockMvc;


    @BeforeAll
    public static void setup() {
        registerController = new RegisterController();
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testDisplayRegister() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register2"));
    }
}
