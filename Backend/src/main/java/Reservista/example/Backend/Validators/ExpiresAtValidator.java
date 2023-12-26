package Reservista.example.Backend.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;


// To check if the expiration date is after the current date
class ExpiresAtValidator implements ConstraintValidator<ExpiresAt, Instant> {

    @Override
    public void initialize(ExpiresAt constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Instant expiresAt, ConstraintValidatorContext constraintValidatorContext) {
        if (expiresAt == null) {
            return false;
        }
        Instant now = Instant.now();
        return expiresAt.isAfter(now);
    }
}
