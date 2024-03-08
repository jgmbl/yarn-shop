package pl.jgmbl.yarnshop.register;

public interface RegisterFormValidator {
    boolean formValidator (String email, String password, String confirmedPassword);
    boolean formValidator (String password, String confirmedPassword);
    boolean formValidator (String email);
}
