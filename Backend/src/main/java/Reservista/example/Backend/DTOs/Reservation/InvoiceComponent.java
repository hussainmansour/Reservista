package Reservista.example.Backend.DTOs.Reservation;


import org.springframework.data.util.Pair;

public interface InvoiceComponent {
    public Pair<Double,String> calculate_price();

}
