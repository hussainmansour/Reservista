package Reservista.example.Backend.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static Reservista.example.Backend.Config.ValidationUtil.invalidUsername;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface Username {

    String message() default invalidUsername;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
