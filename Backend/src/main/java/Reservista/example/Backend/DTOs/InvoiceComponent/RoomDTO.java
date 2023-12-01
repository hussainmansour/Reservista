package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;

import java.util.Vector;
import org.antlr.v4.runtime.misc.Pair;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String type;
    private Vector<Option> options;
    private double price;
    private String roomID;

    public Pair<Double, String> calculate_price() {
        double total = price;
        StringBuilder s = new StringBuilder(type + "\t\t\t" + price + "\n");
        for (int i = 0; i < options.size(); i++) {
            Pair<Double, String> priceAndDetails = options.elementAt(i).calculate_price();
            total += priceAndDetails.a;
            s.append(priceAndDetails.b);
        }
        return new Pair<>(total, s.toString());
    }
}
