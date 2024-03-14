package pl.jgmbl.yarnshop.login.resetpassword;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class TestResetPassword {

    @MockBean
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ResetPasswordController()).build();
    }

    @Test
    public void testDisplayResetPasswordEmailPage() throws Exception {
        mockMvc.perform(get("/login/resetpassword/email").with(user("admin@test.com").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("resetpassword_email"));
    }
}
