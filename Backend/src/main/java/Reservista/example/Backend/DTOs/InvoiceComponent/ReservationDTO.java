package Reservista.example.Backend.DTOs.InvoiceComponent;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.util.Pair;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private long reservationID;

    @NotBlank
    @NotEmpty
    private UUID hotelID;

    @NotNull
    @NotBlank
    private UUID userID;

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

    private String voucherCode;

    private double voucherPercentage;

    private double price;


    public Pair<Double, String> calculate_price() {
        double total = 0;
        StringBuilder s = new StringBuilder();
        s.append("Your Rooms:\n");
        for (int i = 0; i < rooms.size(); i++) {
            Pair<Double, String> priceAndDetails = rooms.get(i).calculate_price();
            total += priceAndDetails.getFirst();
            s.append(priceAndDetails.getSecond());
        }
        int intRefundable = this.refundable ? 1 : 0;
        total += Math.ceil(0.13 * intRefundable * total);
        if (isRefundable()) s.append("\nRefundable\t\t\t" + "13%(+").append(total * 0.13).append(")\n\n");
        if (voucherPercentage != 0)
            s.append("\nVoucher\t\t\t").append(voucherPercentage * 100).append("%(-+").append(total * voucherPercentage).append(")\n");
        total -= total * voucherPercentage;
        s.append("Total \t\t\t").append(total).append("\n\n");

        return Pair.of(total, s.toString());
    }
}

