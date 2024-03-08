package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Component;

@Component
public class CheckPassword {
    public boolean comparePasswords (String password1, String password2) {
        return password1.equals(password2);
    }
}
