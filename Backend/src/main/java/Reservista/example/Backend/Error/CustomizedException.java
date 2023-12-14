package Reservista.example.Backend.Error;

import Reservista.example.Backend.Enums.StatusCode;
import lombok.Data;

@Data
public class CustomizedException extends RuntimeException{
    private int code;
    CustomizedException(StatusCode statusCode){
        super(statusCode.getMessage());
        this.code=statusCode.getCode();
    }
}
