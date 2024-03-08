package pl.jgmbl.yarnshop.register;

public interface FormValidator {
    boolean comparePasswords (String password, String confirmedPassword);
    boolean emailValidator (String email);
    boolean blankFormValidator (String email, String password, String confirmedPassword);
}
