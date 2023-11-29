package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Option {
    private String name;
    private double price;
    public Pair<Double,String> calculate_price(){
        return new Pair<>(this.price,"\t"+name+": "+price+"\n");
    }
}

