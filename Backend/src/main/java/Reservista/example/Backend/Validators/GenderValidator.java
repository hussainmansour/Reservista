package Reservista.example.Backend.Validators;

import Reservista.example.Backend.Enums.Genders;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, Genders> {

    @Override
    public void initialize(Gender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Genders gender, ConstraintValidatorContext context) {
        if (gender == null) {
            return false;
        }
        for (Genders validGender : Genders.values()) {
            if (validGender.equals(gender)) {
                return true;
            }
        }
        return false;
    }
}
