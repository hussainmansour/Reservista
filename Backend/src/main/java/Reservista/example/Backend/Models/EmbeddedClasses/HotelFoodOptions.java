package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HotelFoodOptions {
    private int breakfastPrice;
    private int lunchPrice;
    private int dinnerPrice;
}
