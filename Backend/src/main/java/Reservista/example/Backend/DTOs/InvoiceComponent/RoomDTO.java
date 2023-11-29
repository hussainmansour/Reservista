package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;

import java.util.Vector;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomDTO {
    private String type;
    private Vector<Option> options;
    private double price;
    private String roomID;

    public Pair<Double,String> calculate_price(){
        double total=price;
        StringBuilder s= new StringBuilder(type + "\t\t\t" + price + "\n");
        for(int i=0;i<options.size();i++){
            Pair<Double,String> priceAndDetails=options.elementAt(i).calculate_price();
            total+=priceAndDetails.first;
            s.append(priceAndDetails.second);
        }
        return new Pair<>(total, s.toString());
    }
}
