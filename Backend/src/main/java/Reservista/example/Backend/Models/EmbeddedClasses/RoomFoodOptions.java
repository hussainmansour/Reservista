package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class RoomFoodOptions {
    private boolean hasBreakfast = false;
    private boolean hasLunch = false;
    private boolean hasDinner = false;
}
