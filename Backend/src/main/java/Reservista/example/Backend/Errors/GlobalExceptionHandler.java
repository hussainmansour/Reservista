package Reservista.example.Backend.Errors;

import Reservista.example.Backend.DTOs.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.RegistrationResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> fieldErrors = new HashMap<>();

        // Iterate over the errors and extract field name and default message set on annotations of these fields
        for (FieldError error : result.getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        String className = result.getTarget() != null ? result.getTarget().getClass().getSimpleName() : null;

        if ("RegistrationRequestDTO".equals(className)){

            ObjectMapper mapper = new ObjectMapper();
            RegistrationResponseDTO responseDTO =mapper.convertValue(fieldErrors,RegistrationResponseDTO.class);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrors);

    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDatabaseExceptions(DataAccessException ex){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"response\":\"error\"}");

    }

    @ExceptionHandler(CredentialsException.class)
    public ResponseEntity<Object> credentialsException(CredentialsException ex) {

        RegistrationResponseDTO responseDTO
                = RegistrationResponseDTO.builder().response(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( responseDTO);
    }

}
