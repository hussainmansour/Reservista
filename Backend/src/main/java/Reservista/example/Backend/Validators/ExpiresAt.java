package Reservista.example.Backend.Validators;


import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExpiresAtValidator.class})
public @interface ExpiresAt {
    String message() default "Invalid expiration date";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
