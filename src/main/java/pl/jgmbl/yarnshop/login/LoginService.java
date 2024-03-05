package pl.jgmbl.yarnshop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public String submitLoginForm (LoginForm loginForm, User user, Model model) {
        Optional<User> optionalUser = userRepository.findByEmail(loginForm.getEmail());

        if (checkIfUserExists(user.getEmail())) {

            User existingUser = optionalUser.get();

            if (existingUser.getPassword().equals(loginForm.getPassword())) {
                return "redirect:/account";
            }
        }

        model.addAttribute("InvalidCredentials", true);
        return "redirect:/login";
    }

    boolean checkIfUserExists (String email) {
        Optional<User> doesUserExists = userRepository.findByEmail(email);

        return doesUserExists.isPresent();
    }
}


