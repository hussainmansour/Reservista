package Reservista.example.Backend.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GmailValidator.class)
public @interface Gmail {
    String message() default "please enter your gmail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}