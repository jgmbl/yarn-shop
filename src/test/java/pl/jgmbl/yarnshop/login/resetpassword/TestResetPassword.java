package pl.jgmbl.yarnshop.login.resetpassword;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jgmbl.yarnshop.HashPasswordService;
import pl.jgmbl.yarnshop.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class TestResetPassword {

    @MockBean
    private MockMvc mockMvc;

    @Autowired
    ResetPasswordService resetPasswordService;

    @Autowired
    HashPasswordService hashPasswordService;

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

    @Test
    public void testDisplayResetPasswordPage() throws Exception {
        mockMvc.perform(get("/login/resetpassword/password").with(user("admin@test.com").password("adminadmin@").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("resetpassword_passwords"));
    }

    @Test
    public void updatePasswordWithValidPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String email = "admin@test.com";
        String password = "password@";

        Optional<User> optionalUser = resetPasswordService.updatePassword(email, password);
        Assertions.assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        
        Assertions.assertTrue(hashPasswordService.checkHashedPasswords(password, user.getPassword()));
    }
}
