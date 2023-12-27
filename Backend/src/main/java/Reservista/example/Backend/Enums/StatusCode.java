package Reservista.example.Backend.Enums;

import Reservista.example.Backend.DTOs.ErrorDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import lombok.Getter;

@Getter
public enum StatusCode {



    EMAIL_ALREADY_EXIST(11, "Email already exists"),
    USERNAME_ALREADY_EXIST(12, "Username already exists"),
    ACCOUNT_DEACTIVATED(200, "This account already exists and needs to be activated, check your email!"),
    ACCOUNT_BLOCKED(14, "This account is blocked"),
    SUCCESSFUL_LOGIN(200, "Login completed successfully"),
    BAD_USER_CREDENTIALS(400,"Bad credentials"),
    EMAIL_NOT_REACHED(20,"Couldn't reach your email"),
    REGISTRATION_RACE_CONDITION(21, "Email or username already exists"),
    SUCCESS(200, "success"),
    NOT_FOUND(404, "Voucher not found"),
    SERVER_ERROR(500, "Server error"),
    CREDENTIAL_ERROR(1, "Credential errors "),
    NOT_REGISTERED_USER(2, "This email didn't register"),
    WRONG_VERIFICATION_CODE(3, "Wrong verification code"),
    EXPIRED_VERIFICATION_COD(4, "This code has expired"),
    INVALID_REQUEST(5, "Invalid request,there isn't Deactivated account of this email or there this account already activated"),
    INVALID_ARGUMENT(400, "Invalid argument"),

    EXPIREDCODE(330 , "This code is expired"),
    USEDVOUCHER(99, "This code is used already"),
    NOT_AVAILABLE(30,"Rooms are not avaialble"),
    UNAUTHORIZED(401 , "Incorrect username or password"),

    STRIPE_PAYMENT_INTENT_FAILED(60,"Failed to create payment intent"),
    STRIPE_PAYMENT_INTENT_SUCCESSFUL(61, "Payment intent created"),
    TEST_CODE(0,"testing message"),



    // Admin Status codes
    VOUCHER_ALREADY_EXISTS(23,"Voucher already exists"),

    ADMIN_ALREADY_EXISTS(24,"Admin already exists"),

    UNSUPPORTED_SERVICE(22,"Chosen hotel doesn't have fully refundable option");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

//
    public ResponseDTO<Void> getRespond() {
        return new ResponseDTO<>(code,message,null);
    }
//    public ResponseDTO<Object> getRespond() {
//        return new ResponseDTO<Object>(code,message,null);
//    }
    public ErrorDTO<String> getError (){
        return new ErrorDTO<String> (code,message);
    }
}