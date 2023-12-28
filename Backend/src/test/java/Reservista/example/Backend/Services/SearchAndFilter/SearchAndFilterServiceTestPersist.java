package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;
import Reservista.example.Backend.Models.EmbeddedClasses.HotelFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import Reservista.example.Backend.Models.EntityClasses.Location;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
public class SearchAndFilterServiceTestPersist {

    @Autowired
    private HotelSearchService hotelSearchService;

    @Autowired
    private HotelSearchFactory hotelSearchFactory;

    @Autowired
    private RoomSearchService roomSearchService;

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @Autowired
    private TestEntityManager testEntityManager;

//    @BeforeEach
//    void setUp() {
//        Hotel hotel = Hotel.builder()
//                .name("Hotel")
//                .id(UUID.fromString("d10a9c49-add1-44bd-98c6-2674c5b8fd78"))
//                .rating(5)
//                .reviewCount(10)
//                .starRating(5)
//                .address("123 Main Street") // Add the address
//                .areaDescription(Set.of("Downtown", "Nearby Park")) // Add area descriptions
//                .fullyRefundableRate(50) // Add fully refundable rate
//                .isFullyRefundable(true) // Add fully refundable status
//                .hotelFoodOptions(new HotelFoodOptions()) // Add hotel food options
//                .location(Location.builder()
//                        .city("Paris") // Add city
//                        .country("France") // Add country
//                        .build()) // Add location
//                .hotelImages(Set.of()) // Add hotel images
//                .roomDescriptions((Set<RoomDescription>) RoomDescription.builder()
//                        .title("Single") // Add room type
//                        .price(100) // Add price
//                        .roomCount(10) // Add number of rooms
//                        .capacity(2) // Add capacity
//                        .build()) // Add room description
//                 .build();
//        testEntityManager.persist(hotel);
//    }

    @Test
    public void testSearchHotels() {
//        HotelSearchCriteriaDTO searchCriteria = new HotelSearchCriteriaDTO();
//        searchCriteria.setCity("Paris");
//        searchCriteria.setCountry("France");
//        searchCriteria.setCheckIn(Instant.parse("2024-01-01T00:00:00Z"));
//        searchCriteria.setCheckOut(Instant.parse("2024-01-05T00:00:00Z"));
//        searchCriteria.setNumberOfRooms(1);
//        searchCriteria.setNumberOfTravelers(1);
//        searchCriteria.setMinPrice(0);
//        searchCriteria.setMaxPrice(100000);
//        searchCriteria.setMinStars(0);
//        searchCriteria.setMaxStars(5);
//        searchCriteria.setMinRating(0);
//        searchCriteria.setMaxRating(10);
//        searchCriteria.setPageSize(20);
//
//        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);
//
//        // Assert
//        assertNotNull(result);
//        assertNotNull(result.getHotels());
//        Assertions.assertEquals(1, result.getHotels().size());
     }



}
