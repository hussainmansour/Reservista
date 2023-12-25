package Reservista.example.Backend.Error;

import Reservista.example.Backend.DTOs.Registration.RegistrationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
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
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        Map<String, String> fieldErrors = new HashMap<>();

        // Iterate over the errors and extract field name and default message set on annotations of these fields
        for (FieldError error : result.getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        if (result.getTarget().getClass().getSimpleName().equals("RegistrationRequestDTO")){
            ObjectMapper objectMapper = new ObjectMapper();
            RegistrationResponseDTO registrationResponseDTO = objectMapper.convertValue(fieldErrors,RegistrationResponseDTO.class);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(400)
                            .data(registrationResponseDTO)
                            .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .status(400)
                .data(fieldErrors)
                .build());
    }


     @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDatabaseExceptions(DataAccessException ex){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StatusCode
                        .SERVER_ERROR
                        .getRespond());

    }

    @ExceptionHandler(RegistrationCredentialsException.class)
    public ResponseEntity<Object> credentialsException(RegistrationCredentialsException ex) {

        RegistrationResponseDTO responseDTO
                = RegistrationResponseDTO.builder().response(ex.getMessage()).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .status(500)
                        .message(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(DeactivatedAccountException.class)
    public ResponseEntity<Object> deactivatedAccountException(DeactivatedAccountException ex) {

        RegistrationResponseDTO responseDTO
                = RegistrationResponseDTO.builder().response(ex.getMessage()).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StatusCode
                        .ACCOUNT_DEACTIVATED
                        .getRespond());

    }
    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<ResponseDTO> customizedException(CustomizedException ex){
        return ResponseEntity
                .status(HttpStatus.OK).body(ResponseDTO.builder().status(ex.getCode()).message(ex.getMessage()).build());
    }

}
