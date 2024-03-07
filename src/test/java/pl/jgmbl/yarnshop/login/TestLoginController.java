package pl.jgmbl.yarnshop.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jgmbl.yarnshop.user.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLoginController {

    @MockBean
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    @Test
    public void testDisplayLoggingPage() throws Exception {
        mockMvc.perform(get("/login").with(user("admin").password("password").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("login2"));
    }

    @Test
    public void testLogging() throws Exception {
        mockMvc.perform(get("/login").with(user("admin").password("password").roles("USER","ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void testSubmitButton() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("email", "admin@test.com")
                        .param("password", "password"))
                .andExpect(view().name("account2"))
                .andExpect(status().isOk());
    }
}