package Reservista.example.Backend.Validators;

import jakarta.validation.*;
import java.lang.annotation.*;

import static Reservista.example.Backend.Config.ValidationUtil.invalidAge;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeValidator.class})
public @interface Age {
    String message() default invalidAge;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
