package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomFoodOptions {
    private boolean hasBreakfast = false;
    private boolean hasLunch = false;
    private boolean hasDinner = false;
}
