package Reservista.example.Backend.Exceptions;


import Reservista.example.Backend.DTOs.ErrorDTO;
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
    public ResponseEntity<ErrorDTO<String>> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorDTO<String> error = StatusCode.LOGIN_FAILED.getError();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // todo: to be removed
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorDTO<String>> handleBadCredentialsException(DisabledException ex) {
        ErrorDTO<String> error = StatusCode.ACCOUNT_DEACTIVATED.getError();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED).body(error);

    }

}
