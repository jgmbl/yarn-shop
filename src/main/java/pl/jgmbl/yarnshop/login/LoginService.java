package pl.jgmbl.yarnshop.login;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.HashPasswordService;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@EnableRedisHttpSession
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPasswordService hashPasswordService;

//    public String logInUser(LoginForm loginForm, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        Optional<User> optionalUser = userRepository.findByEmail(loginForm.getEmail());
//
//        if (optionalUser.isPresent()) {
//
//            User existingUser = optionalUser.get();
//
//            if (hashPasswordService.checkHashedPasswords(loginForm.getPassword(), existingUser.getPassword())) {
//                return "redirect:/account";
//            }
//        }
//
//        model.addAttribute("InvalidCredentials", true);
//        return "login2";
//    }

    public String logInUser(LoginForm loginForm, HttpSession httpSession, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<User> optionalUser = userRepository.findByEmail(loginForm.getEmail());

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();

            if (hashPasswordService.checkHashedPasswords(loginForm.getPassword(), existingUser.getPassword())) {
                httpSession.setAttribute("username", loginForm.getEmail());
                return "redirect:/account";
            }
        }

        model.addAttribute("InvalidCredentials", true);
        return "login2";
    }

}


