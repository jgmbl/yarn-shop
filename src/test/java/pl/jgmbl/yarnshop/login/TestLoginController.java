package pl.jgmbl.yarnshop.login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestLoginController {
    private static LoginController loginController;
    private static MockMvc mockMvc;


    @BeforeAll
    public static void setup() {
        loginController = new LoginController();
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testDisplayLoggingPage()throws Exception {
        mockMvc.perform(get("/login").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("login2"));
    }

    @Test
    public void testLogging() throws Exception {
        mockMvc
                .perform(get("/login").with(user("admin").password("password").roles("USER","ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void testSubmitButton() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", "admin@test.com")
                .param("password", "password"))
                .andExpect(redirectedUrl("/account"))
                .andExpect(status().isOk());

    }
}
