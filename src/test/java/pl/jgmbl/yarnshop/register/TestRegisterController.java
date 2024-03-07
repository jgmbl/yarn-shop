package pl.jgmbl.yarnshop.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class TestRegisterController {
    private static RegisterController registerController;
    @MockBean
    private static MockMvc mockMvc;
    @Autowired
    PasswordSecurity passwordSecurity;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RegisterController()).build();
    }

    @Test
    public void testDisplayRegister() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register2"));
    }

    @Test
    public void testPasswordHashing() {
        String hashedPassword = passwordSecurity.hashpassword("password");
        String dehashedPassword = passwordEncoder.encode(hashedPassword);

        boolean passwordMatches = passwordEncoder.matches("password", dehashedPassword);

        Assertions.assertTrue(passwordMatches);

    }
}
