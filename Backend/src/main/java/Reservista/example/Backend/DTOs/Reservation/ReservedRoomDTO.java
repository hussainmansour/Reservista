package Reservista.example.Backend.DTOs.Reservation;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ReservedRoomDTO {
    private boolean hasBreakfast = false;
    private boolean hasLunch = false;
    private boolean hasDinner = false;
    private int totalPrice;

}
