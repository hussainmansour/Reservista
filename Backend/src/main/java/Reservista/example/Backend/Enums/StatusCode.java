package Reservista.example.Backend.Enums;

import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import lombok.Getter;

@Getter
public enum StatusCode {

    INVALID_EMAIL(10, "please enter your gmail"),
    EMAIL_ALREADY_EXIST(11, "Email already exists"),
    USERNAME_ALREADY_EXIST(12, "Username already exists"),
    ACCOUNT_DEACTIVATED(200, "This account already exists and needs to be activated, check your email!"),
    ACCOUNT_BLOCKED(14, "This account is blocked"),
    INVALID_USERNAME(15, "please enter a username that does not include @"),
    INVALID_NATIONALITY(21, "Invalid nationality"),
    INVALID_BIRTHDATE(16, "Invalid age"),
    INVALID_FIRSTNAME(17, "please provide your first name"),
    WEAK_PASSWORD(18, "Please enter a strong password"),
    SUCCESSFUL_REGISTRATION(200, "Registration complete, verify your email!"),
    SUCCESSFUL_LOGIN(200, "Login completed successfully"),
    BAD_USER_CREDENTIALS(400,"Bad credentials"),
    EMAIL_NOT_REACHED(20,"Couldn't reach your email"),
    REGISTRATION_RACE_CONDITION(21, "Email or username already exists"),
    SUCCESS(200, "success"),
    NOT_FOUND(404, "Not found"),
    SERVER_ERROR(500, "Server error"),
    CREDENTIAL_ERROR(1, "Credential errors "),
    NOT_REGISTERED_USER(2, "This email didn't register"),
    WRONG_VERIFICATION_CODE(3, "Wrong verification code"),
    EXPIRED_VERIFICATION_COD(4, "This code has expired"),
    INVALID_REQUEST(5, "Invalid request,there isn't Deactivated account of this email or there this account already activated"),
    INVALID_ARGUMENT(400, "Invalid argument"),
    UNSUPPORTED_SERVICE(22,"choosen hotel doesn't have fully refundable option"),
    UNAUTHORIZED(401 , "Incorrect username or password");



    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO<Void> getRespond() {
        return new ResponseDTO<>(code,message,null);
    }
}