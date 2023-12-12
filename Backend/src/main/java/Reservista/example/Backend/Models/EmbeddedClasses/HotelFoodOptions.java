package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class HotelFoodOptions {
    private int breakfastPrice;
    private int lunchPrice;
    private int dinnerPrice;
}
