package Reservista.example.Backend.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "optional_service")
public class OptionalService {

    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

}
