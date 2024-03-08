package pl.jgmbl.yarnshop.register;

import org.springframework.stereotype.Component;

@Component
public class BlankFormValidator {
    boolean isFormNotBlank (String property, String property1, String property2) {
        return property != null && property1 != null && property2 != null;
    }
}
