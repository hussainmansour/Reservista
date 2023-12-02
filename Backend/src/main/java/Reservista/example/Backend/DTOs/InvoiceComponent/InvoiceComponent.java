package Reservista.example.Backend.DTOs.InvoiceComponent;


import org.springframework.data.util.Pair;

public interface InvoiceComponent {
    public Pair<Double,String> calculate_price();

}
