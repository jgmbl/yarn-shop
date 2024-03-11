package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Component;

@Component
public class LengthPasswordValidator implements PasswordValidator{
    public LengthPasswordValidator() {
    }

    @Override
    public boolean validate(String password) {
        return password.length() > 7;
    }
}
