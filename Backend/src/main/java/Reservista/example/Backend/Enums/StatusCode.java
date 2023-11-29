package Reservista.example.Backend.Enums;

public enum StatusCode {

    INVALID_EMAIL(10,"please provide your gmail"),
    EMAIL_ALREADY_EXIST(11,"email already exists"),
    USERNAME_ALREADY_EXIST(12,"username already exists"),
    ACCOUNT_DEACTIVATED(13,"this account already exists and needs to be activated, check your email"),
    ACCOUNT_BLOCKED(14,"this account is blocked"),
    INVALID_USERNAME(15,"enter a username that does not include @"),
    INVALID_BIRTHDATE(16,"invalid birthdate"),
    INVALID_FIRSTNAME(17,"please provide your first name"),
    WEAK_PASSWORD(18,"weak password"),
    SUCCESSFUL_REGISTRATION(19,"registration completed, verify your email");

    private final int code;
    public final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

//    public String getMessage() {
//        return message;
//    }
//    public int getCode() {
//        return code;
//    }
}
