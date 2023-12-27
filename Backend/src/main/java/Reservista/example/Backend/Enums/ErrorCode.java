package Reservista.example.Backend.Enums;

import Reservista.example.Backend.DTOs.ErrorDTO;
import lombok.Getter;

@Getter
public enum ErrorCode {

    // Registration errors
    EMAIL_ALREADY_EXIST(1, "Email already exists"), // conflict 409
    USERNAME_ALREADY_EXIST(2, "Username already exists"), // conflict 409
    ACCOUNT_DEACTIVATED(3, "This account already exists and needs to be activated, check your email!"), //conflict 409
    ACCOUNT_BLOCKED(4, "This account is blocked"), //forbidden 403
    REGISTRATION_RACE_CONDITION(5, "Email or username already exists"), // conflict 409

    // OTP errors
    NOT_REGISTERED_USER(10, "This email didn't register"), // 404 not found
    WRONG_VERIFICATION_CODE(11, "Wrong verification code"), // 422 unprocessable entity validation failure
    EXPIRED_VERIFICATION_CODE(12, "This code has expired"), // 410 GONE
    INVALID_OTP_REQUEST(13, "Invalid request,there isn't Deactivated account of this email or there this account already activated"), //400 bad request
    EMAIL_NOT_REACHED(14,"Couldn't reach your email"), //returned in case we fail to send OTPs either during registration or refreshing //service unavailable 503


    // Reservation error codes
    VOUCHER_NOT_FOUND(20, "Voucher not found"), // not found 404
    EXPIRED_CODE(21 , "This code is expired"), // Gone 410 (will not be available again)
    USED_VOUCHER(22, "This code is used already"), //conflict 409
    ROOMS_NOT_AVAILABLE(23,"Rooms are not available"), // not found 404
    STRIPE_PAYMENT_INTENT_FAILED(24,"Failed to create payment intent"), // internal service error 500


    //Login error codes
    LOGIN_FAILED(30 , "Incorrect username or password"),

    //Profile error codes
    PROFILE_NOT_FOUND(40,"Profile not found"),

    // Error code for testing purposes
    TEST_CODE(0,"testing message");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDTO<String> getError (){
        return new ErrorDTO<String> (code,message);
    }
}