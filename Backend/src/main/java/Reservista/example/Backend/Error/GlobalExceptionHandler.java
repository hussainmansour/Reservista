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
//
//        if (result.getTarget().getClass().getSimpleName().equals("RegistrationRequestDTO")){
//            ObjectMapper objectMapper = new ObjectMapper();
//            RegistrationResponseDTO registrationResponseDTO = objectMapper.convertValue(fieldErrors,RegistrationResponseDTO.class);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ResponseDTO.builder()
//                            .status(400)
//                            .data(registrationResponseDTO)
//                            .build());
//        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO.builder()
                .errorCode(100)
                .data(fieldErrors)
                .build());
    }


//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<> handleDatabaseExceptions(DataAccessException ex){
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body();
//
//    }


    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO<String>> globalException(GlobalException ex){

        ErrorDTO<String> error = ex.getStatusCode().getError();

        return ResponseEntity
                .status(ex.getHttpStatus()).body(error);
    }

}
