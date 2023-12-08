package Reservista.example.Backend.Models.EntityClasses;


import Reservista.example.Backend.Models.IDClasses.OptionalServiceId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "optional_service")
@IdClass(OptionalServiceId.class)
public class OptionalService {

    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id" , nullable = false)
    private Hotel hotel;

    @NotNull
    @Column(name = "price")
    private double price;

}
