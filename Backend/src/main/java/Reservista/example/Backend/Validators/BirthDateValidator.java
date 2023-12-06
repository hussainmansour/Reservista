package Reservista.example.Backend.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) return true;

        // Validate that the person is at least 18 years old
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        return !birthDate.isAfter(eighteenYearsAgo);
    }
}