package Reservista.example.Backend;

import Reservista.example.Backend.responds.Respond;

public enum StatusCode {

    INVALID_EMAIL(10,"please provide your gmail"),
    EMAIL_ALREADY_EXIST(11,"email already exists"),
    USERNAME_ALREADY_EXIST(12,"username already exists"),
    ACCOUNT_DEACTIVATED(13,"this account already exists and needs to be activated, check your email"),
    ACCOUNT_BLOCKED(14,"this account is blocked"),
    INVALID_USERNAME(15,"enter a username that does not include @"),
    INVALID_BIRTHDATE(16,"invalid birthdate"),
    INVALID_FIRSTNAME(17,"please provide your first name"),
    WEAK_PASSWORD(18,"Please enter a strong password"),
    SUCCESSFUL_REGISTRATION(19,"registration completed, verify your email"),

    SUCCESS(200,"success"),
    NOT_FOUND(404,"Not found"),
    SERVER_ERROR(500,"Server error"),
    CREDENTIAL_ERROR(1,"Credential errors "),
    NOT_REGISTERED_USER(2,"This email didn't register"),
    WRONG_VERIFICATION_CODE(3,"Wrong verification code"),
    EXPIRED_VERIFICATION_COD(4,"This code has expired"),
    INVALID_REQUEST(5,"Invalid request,there isn't Deactivated account of this email or there this account already activated"),

    INVALID_ARGUMENT(400,"Invalid argument");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
    public Respond getRespond(){
        return new Respond(message,code);
    }


}
