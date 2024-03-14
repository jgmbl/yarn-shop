package pl.jgmbl.yarnshop.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jgmbl.yarnshop.HashPasswordService;

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
    HashPasswordService generatePassword;

    @Autowired
    RegisterService registerService;

    @Autowired
    FormValidator formValidator;

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

        boolean checkedPasswordHashing = generatePassword.checkHashedPasswords(password, hashedPassword);

        Assertions.assertTrue(checkedPasswordHashing);
    }

    @Test
    public void testComparePasswords () {
        Assertions.assertTrue(formValidator.formValidator("password", "password"));
        Assertions.assertFalse(formValidator.formValidator("world", "word"));
    }

    @Test
    public void testIsFormNotBlank () {
        Assertions.assertTrue(formValidator.formValidator("test@mail.com", "password", "password"));
        Assertions.assertFalse(formValidator.formValidator(null, "password", "password"));
        Assertions.assertFalse(formValidator.formValidator("test@mail.com", null, "password"));
        Assertions.assertFalse(formValidator.formValidator("test@mail.com", "password", null));
    }

    @Test
    public void testDoesUserExist () {
        Assertions.assertTrue(formValidator.formValidator("admin@test.com"));
        Assertions.assertFalse(formValidator.formValidator("admin@1test.com"));
    }

    @Test
    public void testRegistrationAccept() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("email", "host@test.com")
                        .param("password", "password")
                        .param("confirmpassword", "password"))
                .andExpect(view().name("redirect:/account"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testRegistrationDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("email", "admin@test.com")
                        .param("password", "password1")
                        .param("confirmpassword", "password"))
                .andExpect(view().name("register2"))
                .andExpect(status().isOk());
    }
}
