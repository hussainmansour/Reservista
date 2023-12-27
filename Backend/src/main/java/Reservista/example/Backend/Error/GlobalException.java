package Reservista.example.Backend.Error;

import Reservista.example.Backend.Enums.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GlobalException extends Exception{
    
    private HttpStatus httpStatus;
    private ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode, HttpStatus httpStatus){
        super();
        this.errorCode = errorCode;
        this.httpStatus=httpStatus;
    }


}
