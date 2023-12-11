package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;

@Embeddable
public class HotelFoodOptions {
    private int breakfastPrice;
    private int lunchPrice;
    private int dinnerPrice;
}
