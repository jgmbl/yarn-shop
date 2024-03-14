package pl.jgmbl.yarnshop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.HashPasswordService;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPasswordService hashPasswordService;

    public String logInUser(LoginForm loginForm, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<User> optionalUser = userRepository.findByEmail(loginForm.getEmail());

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();

            if (hashPasswordService.checkHashedPasswords(loginForm.getPassword(), existingUser.getPassword())) {
                return "redirect:/account";
            }
        }

        model.addAttribute("InvalidCredentials", true);
        return "login2";
    }

}


