package Reservista.example.Backend.Exceptions;


import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDTO<String>> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.ok(
                ResponseDTO.<String> builder()
                        .status(StatusCode.UNAUTHORIZED.getCode())
                        .message(StatusCode.UNAUTHORIZED.getMessage())
                        .build()
        );
    }

    // todo: to be removed
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ResponseDTO<String>> handleBadCredentialsException(DisabledException ex) {
        return ResponseEntity.ok(
                ResponseDTO.<String> builder()
                        .status(StatusCode.ACCOUNT_DEACTIVATED.getCode())
                        .message(StatusCode.ACCOUNT_DEACTIVATED.getMessage())
                        .build()
        );
    }

}
