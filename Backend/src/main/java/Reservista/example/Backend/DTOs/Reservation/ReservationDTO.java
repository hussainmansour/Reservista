package Reservista.example.Backend.DTOs.Reservation;

import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    // Data comes from front-end

//    @NotBlank
//    @NotEmpty
    @NotNull
    private UUID hotelID;

    @NotNull
    @FutureOrPresent
    private Instant checkIn;

    @NotNull
    @FutureOrPresent
    private Instant checkOut;

    @NotNull
    @BooleanFlag
    private boolean refundable;

    private String voucherCode;

    @NotNull
    private UUID roomDescriptionId;

    private List<ReservedRoomDTO> reservedRooms;

    // Data needed to be filled to use it in chain later
    private int roomPrice;
    private String roomTitle;

    private Long reservationID;

    private String userName;

    private String hotelName;

    private HotelFoodOptions hotelFoodOptions;

    private int refundAdditionalPercentage;

    private int voucherPercentage = 0;

    private int price;       //price of the reservation itself

    private int finalPrice;  //price after voucher and refund fees

    private String invoice;

    private String paymentIntentId;


}

