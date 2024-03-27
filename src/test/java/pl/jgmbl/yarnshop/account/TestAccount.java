package pl.jgmbl.yarnshop.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAccount {

    @Autowired
    AccountController accountController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testDisplayAccountPageUnauthenticated() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", null);

        mockMvc.perform(get("/account").session(mockHttpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser
    public void testDisplayAccountPageAuthenticated() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", "admin@test.com");

        mockMvc.perform(get("/account").session(mockHttpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(model().attribute("username", "admin@test.com"));
    }

    @Test
    @WithMockUser
    public void testLogOutUser() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", "admin@test.com");

        mockMvc.perform(get("/logout").session(mockHttpSession).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("login2"));

        Assertions.assertTrue((Boolean) mockHttpSession.getAttribute("username"), (String) null);
    }
}
