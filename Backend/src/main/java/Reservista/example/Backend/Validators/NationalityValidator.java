package Reservista.example.Backend.Validators;

import Reservista.example.Backend.Config.AppConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NationalityValidator implements ConstraintValidator<Nationality, String> {

    private Set<String> validCountries;

    @Override
    public void initialize(Nationality constraintAnnotation) {
        validCountries = AppConfig.validCountries();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null) || validCountries.contains(value.trim());
    }

}