package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public static boolean validateSpecialCharacters(String password) {
        return password.contains("@") || password.contains("#") || password.contains("$");
    }

    public static boolean validateLength(String password) {
        return password.length() > 7;
    }

    public static boolean comparePasswords (String password1, String password2) {
        return password1.equals(password2);
    }
}
