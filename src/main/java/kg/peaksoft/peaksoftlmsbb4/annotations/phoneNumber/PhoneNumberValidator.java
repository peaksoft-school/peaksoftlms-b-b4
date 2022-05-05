package kg.peaksoft.peaksoftlmsbb4.annotations.phoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(phoneNumber.length() == 10 || phoneNumber.length() == 13){
            return phoneNumber.matches("^[0-9/-/+]{9,15}$");
        }else {
            return false;
        }
    }
}
