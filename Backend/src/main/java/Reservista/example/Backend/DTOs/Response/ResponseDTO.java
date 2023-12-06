package Reservista.example.Backend.DTOs.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;
}