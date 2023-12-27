package Reservista.example.Backend.Error;

import Reservista.example.Backend.Enums.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GlobalException extends Exception{

//    private int code;
    private HttpStatus httpStatus;
    private ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode, HttpStatus httpStatus){
//        this.code=errorCode.getCode();
        super();
        this.errorCode = errorCode;
        this.httpStatus=httpStatus;
    }


}
