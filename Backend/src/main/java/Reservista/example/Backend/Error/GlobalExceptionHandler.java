package Reservista.example.Backend.Error;

import Reservista.example.Backend.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        Map<String, String> fieldErrors = new HashMap<>();

        // Iterate over the errors and extract field name and default message set on annotations of these fields
        for (FieldError error : result.getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO.builder()
                .errorCode(100)
                .data(fieldErrors)
                .build());
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO<String>> globalException(GlobalException ex){

        ErrorDTO<String> error = ex.getErrorCode().getError();

        return ResponseEntity
                .status(ex.getHttpStatus()).body(error);
    }



}
