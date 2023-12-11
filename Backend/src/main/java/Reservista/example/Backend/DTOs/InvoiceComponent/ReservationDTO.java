package Reservista.example.Backend.DTOs.InvoiceComponent;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.Instant;
import java.util.List;
import org.springframework.data.util.Pair;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private long reservationID;

    @NotBlank
    @NotEmpty
    private String hotelID;

    @NotNull
    @NotBlank
    private String userID;

    @NotBlank
    @NotEmpty
    private String hotelName;

    @NotNull
    private List<RoomDTO> rooms;

    @NotNull
    @FutureOrPresent
    private Instant checkIn;

    @NotNull
    @FutureOrPresent
    private Instant checkOut;

    @NotNull
    @BooleanFlag
    private boolean refundable;

    private double refundAdditionalPercentage;

    private String voucherCode;
    private double voucherPercentage;

    private double price;
    private long finalPrice;

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Your Rooms:\n");
        for(RoomDTO r :rooms)
            s.append(r.toString());
        s.append("\n");
        if(refundable)
            s.append("\nRefundable additional percentage: + ").append(refundAdditionalPercentage*100).append("%\n");
        if(voucherCode!=null)
            s.append("\nVoucher: -").append(voucherPercentage * 100).append("%\n");
        s.append("Final price: ").append(finalPrice);
        return s.toString();
    }
}

