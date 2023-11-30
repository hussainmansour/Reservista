package Reservista.example.Backend.DTOs.InvoiceComponent;

import lombok.*;

import java.util.Date;
import java.util.Vector;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationDTO {


    private String reservationID;
    private String hotelID;
    private Vector<RoomDTO> rooms;
    private String userID;
    private Date checkIn;
    private Date checkOut;
    private boolean refundable;
    private double voucher;
    private double price;


    public Pair<Double, String> calculate_price() {
        double total = 0;
        StringBuilder s = new StringBuilder();
        s.append("Your Rooms:\n");
        for (int i = 0; i < rooms.size(); i++) {
            Pair<Double, String> priceAndDetails = rooms.elementAt(i).calculate_price();
            total += priceAndDetails.first;
            s.append(priceAndDetails.second);
        }
        int intRefundable = this.refundable ? 1 : 0;
        total += Math.ceil(0.13 * intRefundable * total);
        if (isRefundable()) s.append("\nRefundable\t\t\t" + "13%(+").append(total * 0.13).append(")\n\n");
        if (voucher != 0)
            s.append("\nVoucher\t\t\t").append(voucher * 100).append("%100(-+").append(total * voucher).append(")\n");
        total -= total * voucher;
        s.append("Total \t\t\t").append(total).append("\n\n");

        return new Pair<>(total, s.toString());
    }
}

