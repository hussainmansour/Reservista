package Reservista.example.Backend.Services.SearchAndFilter;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;


import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;

@SpringBootTest
class SearchAndFilterServiceTest {

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @Test
    public void test_searchHotels() {
        // Arrange
        HotelSearchCriteriaDTO searchCriteria = new HotelSearchCriteriaDTO();
        searchCriteria.setCity("Paris");
        searchCriteria.setCountry("France");
        searchCriteria.setCheckIn(Instant.parse("2024-01-01T00:00:00Z"));
        searchCriteria.setCheckOut(Instant.parse("2024-01-05T00:00:00Z"));
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        searchCriteria.setMinPrice(0);
        searchCriteria.setMaxPrice(100000);
        searchCriteria.setMinStars(0);
        searchCriteria.setMaxStars(5);
        searchCriteria.setMinRating(0);
        searchCriteria.setMaxRating(10);
        searchCriteria.setPageSize(20);

//        Pageable pageable = PageRequest.of(0, 20);

        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertEquals(3, result.getHotels().getTotalElements());
    }
}


//class SearchAndFilterServiceTest {
//
//    @Mock
//    private HotelRepository hotelRepository;
//
//    @Mock
//    private HotelMapper hotelMapper;
//
//    @Mock
//    private HotelSearchFactory hotelSearchFactory;
//
//    @InjectMocks
//    private SearchAndFilterService searchAndFilterService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void filterAndSortHotels_WithSortCriteria_ShouldCallSortHotelsByCriteria() {
//        // Arrange
//        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO();
//        searchCriteria.setCity("Cairo");
//        searchCriteria.setCountry("Egypt");
//        searchCriteria.setCheckIn(Instant.parse("2021-05-01"));
//        searchCriteria.setCheckOut(Instant.parse("2021-05-05"));
//        searchCriteria.setNumberOfRooms(1);
//        searchCriteria.setNumberOfTravelers(1);
//        searchCriteria.setMinPrice(0);
//        searchCriteria.setMaxPrice(100000);
//        searchCriteria.setMinStars(0);
//        searchCriteria.setMaxStars(5);
//        searchCriteria.setMinRating(0);
//        searchCriteria.setMaxRating(5);
//        searchCriteria.setSortBy("rating");
//
//
//        Pageable pageable = Pageable.unpaged();
//
//        Page<Hotel> mockHotelPage = mock(Page.class);
//        when(hotelSearchFactory.sortHotelsByCriteria(eq(searchCriteria), eq(pageable))).thenReturn(mockHotelPage);
//
//        // Act
//        SearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria, pageable);
//
//        // Assert
//        verify(hotelSearchFactory, times(1)).sortHotelsByCriteria(eq(searchCriteria), eq(pageable));
//        verify(hotelMapper, never()).hotelToHotelDTO(any());
//    }
//
//    @Test
//    void filterAndSortHotels_WithoutSortCriteria_ShouldCallSearchHotels() {
//        // Arrange
//        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO();
//        searchCriteria.setCity("Cairo");
//        searchCriteria.setCountry("Egypt");
//        searchCriteria.setCheckIn(Instant.parse("2021-05-01"));
//        searchCriteria.setCheckOut(Instant.parse("2021-05-05"));
//        searchCriteria.setNumberOfRooms(1);
//        searchCriteria.setNumberOfTravelers(1);
//        searchCriteria.setMinPrice(0);
//        searchCriteria.setMaxPrice(100000);
//        searchCriteria.setMinStars(0);
//        searchCriteria.setMaxStars(5);
//        searchCriteria.setMinRating(0);
//        searchCriteria.setMaxRating(5);
//        Pageable pageable = Pageable.unpaged();
//
//        Page<Hotel> mockHotelPage = mock(Page.class);
//        when(hotelSearchFactory.searchHotels(eq(searchCriteria), eq(pageable))).thenReturn(mockHotelPage);
//
//        // Act
//        SearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria, pageable);
//
//        // Assert
//        verify(hotelSearchFactory, times(1)).searchHotels(eq(searchCriteria), eq(pageable));
//        verify(hotelMapper, never()).hotelToHotelDTO(any());
//    }
//    @Test
//    public void test_searchHotels() {
//        // Arrange
//        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO();
//        searchCriteria.setCity("Cairo");
//        searchCriteria.setCountry("Egypt");
//        searchCriteria.setCheckIn(Instant.parse("2021-05-01"));
//        searchCriteria.setCheckOut(Instant.parse("2021-05-05"));
//        searchCriteria.setNumberOfRooms(1);
//        searchCriteria.setNumberOfTravelers(1);
//        searchCriteria.setMinPrice(0);
//        searchCriteria.setMaxPrice(100000);
//        searchCriteria.setMinStars(0);
//        searchCriteria.setMaxStars(5);
//        searchCriteria.setMinRating(0);
//        searchCriteria.setMaxRating(5);
//
//        Pageable pageable = PageRequest.of(0, 20);
//        SearchAndFilterService searchAndFilterService = new SearchAndFilterService(hotelRepository, hotelMapper, hotelSearchFactory);
//
//        // Act
//        SearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertNotNull(result.getHotels());
//        assertEquals(0, result.getHotels().getTotalElements());
//    }
//}
//    // Add more test cases based on your specific requirements
