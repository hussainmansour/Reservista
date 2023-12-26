package Reservista.example.Backend.Error;

import Reservista.example.Backend.Enums.StatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Data
public class GlobalException extends Exception{

//    private int code;
    private HttpStatus httpStatus;
    private StatusCode statusCode;

    public GlobalException(StatusCode statusCode, HttpStatus httpStatus){
//        this.code=statusCode.getCode();
        super();
        this.statusCode = statusCode;
        this.httpStatus=httpStatus;
    }


}
