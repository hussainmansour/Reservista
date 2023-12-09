package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.EmbeddedClasses.HotelImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private double rating;

    @Column(name = "review_count")
    private int reviewCount;

    @Min(0)
    @Max(5)
    @Column(name = "star_rating")
    private int starRating;

    @Column(name = "address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "area_description", joinColumns =
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id"))
    @Column(name = "area_description")
    private Set<String> areaDescription;

    @ElementCollection
    @CollectionTable(name = "hotel_image", joinColumns =
    @JoinColumn(name = "hotel_id" , referencedColumnName = "id"))
    @AttributeOverrides({
            @AttributeOverride(
                    name = "source",
                    column = @Column(name = "image_source")
            ),
            @AttributeOverride(
                    name = "caption",
                    column = @Column(name = "image_caption")
            ),
    })
    private Set<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<OptionalService> optionalServices;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private Set<Review> reviews;


    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "location_city", referencedColumnName = "city" , nullable = false),
            @JoinColumn(name = "location_country", referencedColumnName = "country" , nullable = false)
    })
    private Location location;
    @BooleanFlag
    private boolean haveFullyRefundOption;
    @Max(1)
    private double additionalRefundPercentage;
}
