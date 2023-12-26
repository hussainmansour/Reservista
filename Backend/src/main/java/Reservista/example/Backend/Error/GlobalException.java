package Reservista.example.Backend.Error;

import Reservista.example.Backend.Enums.StatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GlobalException extends Exception{

//    private int code;
    private HttpStatus httpStatus;
    private StatusCode statusCode;

    public GlobalException(StatusCode statusCode, HttpStatus httpStatus){
//        this.code=statusCode.getCode();
        this.statusCode = statusCode;
        this.httpStatus=httpStatus;
    }
}
