package Reservista.example.Backend.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<Gmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.toLowerCase().endsWith("@gmail.com");
    }
}
