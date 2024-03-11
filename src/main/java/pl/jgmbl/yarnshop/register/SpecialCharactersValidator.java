package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Component;

@Component
public class SpecialCharactersValidator implements PasswordValidator{

    public SpecialCharactersValidator() {
    }

    @Override
    public boolean validate(String password) {
        return password.contains("@") || password.contains("#") || password.contains("$");
    }
}
