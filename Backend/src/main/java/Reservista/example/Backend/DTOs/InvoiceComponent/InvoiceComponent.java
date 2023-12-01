package Reservista.example.Backend.DTOs.InvoiceComponent;

import org.antlr.v4.runtime.misc.Pair;

public interface InvoiceComponent {
    public Pair<Double,String> calculate_price();

}
