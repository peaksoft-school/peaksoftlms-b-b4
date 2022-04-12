package kg.peaksoft.peaksoftlmsbb4.annotations.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext constraintValidatorContext) {
        return password.length() >= 6 && password.length() <= 20;
    }
}
