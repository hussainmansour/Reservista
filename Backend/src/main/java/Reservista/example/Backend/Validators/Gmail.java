package Reservista.example.Backend.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Email
@NotBlank
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GmailValidator.class)
public @interface Gmail {
    String message() default "Email must end with @gmail.com";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}