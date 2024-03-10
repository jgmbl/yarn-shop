package pl.jgmbl.yarnshop.register;

public class SpecialCharactersValidator implements PasswordValidator{

    @Override
    public boolean validate(String password) {
        return password.contains("@") || password.contains("#") || password.contains("$");
    }
}
