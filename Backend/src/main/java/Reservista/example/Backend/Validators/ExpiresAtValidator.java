package Reservista.example.Backend.Validators;

import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;

class ExpiresAtValidator implements ConstraintValidator<ExpiresAt, VoucherDTO> {

    @Override
    public void initialize(ExpiresAt constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(VoucherDTO voucherDTO, ConstraintValidatorContext context) {
        return voucherDTO.getExpiresAt() != null && voucherDTO.getExpiresAt().isAfter(Instant.now());
    }
}
