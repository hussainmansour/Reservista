package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;
import org.springframework.data.util.Pair;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private String name;
    private double price;
    public Pair<Double,String> calculate_price(){
        return Pair.of(this.price,"\t"+name+": "+price+"\n");
    }
}

