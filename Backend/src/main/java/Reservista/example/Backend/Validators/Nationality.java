package Reservista.example.Backend.Validators;

import jakarta.validation.*;
import java.lang.annotation.*;

import static Reservista.example.Backend.Config.ValidationUtil.invalidNationality;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NationalityValidator.class})
public @interface Nationality {
    String message() default invalidNationality;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
