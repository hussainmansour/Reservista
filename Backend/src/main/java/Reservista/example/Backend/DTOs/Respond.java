package Reservista.example.Backend.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class Respond<T> {
    private int status;
    private String message;
    private T data;

}
