package Reservista.example.Backend.Validators;

import Reservista.example.Backend.Config.ValidationUtil;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static Reservista.example.Backend.Config.ValidationUtil.invalidEmail;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GmailValidator.class)
public @interface Gmail {
    String message() default invalidEmail;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}