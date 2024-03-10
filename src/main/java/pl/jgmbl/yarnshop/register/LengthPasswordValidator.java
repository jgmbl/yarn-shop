package pl.jgmbl.yarnshop.register;

public class LengthPasswordValidator implements PasswordValidator{
    @Override
    public boolean validate(String password) {
        return password.length() > 7;
    }
}
