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

    private List<Boolean> options;

    private double price;

    private double optionsTotalPrice;


    @Override
    public String toString(){
        StringBuilder s = new StringBuilder(title + ": " + price + "\n");
        if(options!=null && options.size()!=0){
            for (OptionDTO o:options){
                s.append(o.toString());
            }
            s.append("\tprice after optional services: ").append(optionsTotalPrice+price).append("\n");
        }
        return s.toString();
    }
}
