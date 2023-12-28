package Reservista.example.Backend.DTOs.Profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpcomingHistoryReservationDTO {
    private Long reservationID;
    private String hotelName;
    private String roomTitle;
    private Instant reservationDate;
    private Instant checkIn;
    private Instant checkOut;
    private int noOfRooms;
    private int price;
    private String invoice;
}
