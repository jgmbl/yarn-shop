package pl.jgmbl.yarnshop.account.deletion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAccountDeletion {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountDeletionService accountDeletionService;

    @Autowired
    private MockHttpServletRequest request;

    @Autowired
    MockHttpSession session;

    public TestAccountDeletion(MockHttpServletRequest request) {
        this.request = request;
    }

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
        session.setAttribute("username", "admin@test.com");


        Mockito.when(accountDeletionService.deleteAccountBySessionId()).thenReturn(true);
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/accountdeletion")
                        .session((MockHttpSession) Objects.requireNonNull(request.getSession())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
