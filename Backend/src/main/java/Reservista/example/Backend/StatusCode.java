package Reservista.example.Backend;

import Reservista.example.Backend.responds.Respond;

public enum StatusCode {
    SUCCESS(200,"success"),
    NOT_FOUND(404,"Not found"),
    SERVER_ERROR(500,"Server error"),
    CREDENTIAL_ERROR(1,"Credential errors "),
    NOT_REGISTERED_USER(2,"This email didn't register"),
    WRONG_VERIFICATION_CODE(3,"Wrong verification code"),
    EXPIRED_VERIFICATION_COD(4,"This code has expired"),
    INVALID_REQUEST(5,"Invalid request,there isn't Deactivated account of this email or there this account already activated"),

    INVALID_ARGUMENT(400,"Invalid argument");



    private  final int code;
    private  String message;
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
