package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;
import org.springframework.data.util.Pair;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    private String name;
    private double price;
    @Override
    public String toString(){
        return "\t"+name+": "+price+"\n";
    }
}

