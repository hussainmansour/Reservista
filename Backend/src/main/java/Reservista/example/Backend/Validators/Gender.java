package Reservista.example.Backend.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static Reservista.example.Backend.Config.ValidationUtil.invalidAge;
import static Reservista.example.Backend.Config.ValidationUtil.invalidGender;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GenderValidator.class})
public @interface Gender {
    String message() default invalidGender;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
