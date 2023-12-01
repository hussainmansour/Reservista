package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;
import org.antlr.v4.runtime.misc.Pair;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private String name;
    private double price;
    public Pair<Double,String> calculate_price(){
        return new Pair<>(this.price,"\t"+name+": "+price+"\n");
    }
}

