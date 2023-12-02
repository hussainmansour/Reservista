package Reservista.example.Backend.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CountryValidator implements ConstraintValidator<Country, String> {

    @Autowired
    private Set<String> validCountries;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return (value == null) || validCountries.contains(value);

    }
}
