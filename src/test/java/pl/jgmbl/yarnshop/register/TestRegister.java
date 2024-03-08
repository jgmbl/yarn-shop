package pl.jgmbl.yarnshop.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class TestRegister {

    @Autowired
    RegisterController registerController;

    private static MockMvc mockMvc;

    @Autowired
    HashPassword generatePassword;

    @Autowired
    RegisterService registerService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testDisplayRegister() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register2"));
    }

    @Test
    public void testPasswordHashing() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "password";
        byte[] hashedPassword = generatePassword.hashPassword(password);

        boolean checkedPasswordHashing = generatePassword.checkPasswordHashing(password, hashedPassword);

        Assertions.assertTrue(checkedPasswordHashing);
    }

    @Test
    public void testComparePasswords () {
        Assertions.assertTrue(registerService.comparePasswords("password", "password"));
        Assertions.assertFalse(registerService.comparePasswords("world", "word"));
    }

    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("email", "admin@test.com")
                        .param("password", "password")
                        .param("confirmpassword", "password"))
                .andExpect(view().name("account2"))
                .andExpect(status().isOk());
    }
}
