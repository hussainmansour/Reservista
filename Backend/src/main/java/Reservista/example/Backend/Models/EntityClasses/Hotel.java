package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class  Hotel {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;


    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private double rating = 0;

    @Column(name = "review_count")
    private int reviewCount = 0;

    @Min(0)
    @Max(5)
    @Column(name = "star_rating")
    private int starRating;

    @Column(name = "address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "area_description", joinColumns =
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id"))
    @Column(name = "area_description" , nullable = false)
    private Set<String> areaDescription;

    @OneToMany(mappedBy = "hotel" , cascade = CascadeType.ALL)
    private Set<HotelImage> hotelImages;

    @Min(0)
    @Max(100)
    @Column(name = "fully_refundable_rate")
    private int fullyRefundableRate = 0;

    @Column(name = "is_fully_refundable")
    private boolean isFullyRefundable = false;

    @Embedded
    private HotelFoodOptions hotelFoodOptions;


    @ManyToOne(optional = false , cascade = CascadeType.PERSIST)
    @JoinColumns({
            @JoinColumn(name = "location_city", referencedColumnName = "city" , nullable = false),
            @JoinColumn(name = "location_country", referencedColumnName = "country" , nullable = false)
    })
    private Location location;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private Set<RoomDescription> roomDescriptions;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
}
