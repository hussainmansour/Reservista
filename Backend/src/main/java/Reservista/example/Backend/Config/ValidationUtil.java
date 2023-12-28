package Reservista.example.Backend.Config;

import lombok.Data;

@Data
public final class ValidationUtil {

    public static final String invalidEmail ="Please enter a gmail.";
    public static final String invalidUsername ="Please enter a valid username. It should be at least two characters long and should not include the '@' symbol.";
    public static final String invalidPassword="Please enter a strong password. Password should be at least 8 characters long and contain at least one uppercase, lowercase, digit, and special character";
    public static final String invalidAge="Invalid Age";
    public static final String invalidGender="invalid gender";
    public static final String invalidNationality="invalid nationality";
    public static final int validationErrorCode = 100;
}
