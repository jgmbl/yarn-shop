package pl.jgmbl.yarnshop.register;

public interface PasswordValidator {
    boolean checkPassword(String password1, String password2);
}
