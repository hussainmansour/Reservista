package Reservista.example.Backend.DTOs.InvoiceComponent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.util.Pair;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    @NotBlank
    @NotEmpty
    private String title;

    private List<Option> options;

    private double price;



    public Pair<Double, String> calculate_price() {
        double total = price;
        StringBuilder s = new StringBuilder(title + "\t\t\t" + price + "\n");
        for (int i = 0; i < options.size(); i++) {
            Pair<Double, String> priceAndDetails = options.get(i).calculate_price();
            total += priceAndDetails.getFirst();
            s.append(priceAndDetails.getSecond());
        }
        return  Pair.of(total, s.toString());
    }
}
