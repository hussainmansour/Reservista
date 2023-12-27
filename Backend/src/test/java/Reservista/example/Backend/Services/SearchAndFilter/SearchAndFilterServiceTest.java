package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DTOs.SearchAndFilter.HotelDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelIdentifierWithSearchCriteriaDTO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchResultDTO;

@SpringBootTest
class SearchAndFilterServiceTest {

    @Autowired
    private SearchAndFilterService searchAndFilterService;

    @Test
    public void test1_searchHotels() {
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
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);
     }

        @Test
    public void test2_searchHotelsAndSortByPriceASC() {
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
        searchCriteria.setSortBy("price");
        searchCriteria.setSortOrder("asc");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

        }

    @Test
    public void test3_searchHotelsAndSortByPriceDESC() {
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
        searchCriteria.setSortBy("price");
        searchCriteria.setSortOrder("DESC");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

    }

    @Test
    public void test4_searchHotelsAndSortByRatingASC() {
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
        searchCriteria.setSortBy("rating");
        searchCriteria.setSortOrder("asc");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

    }
    @Test
    public void test5_searchHotelsAndSortByRatingDESC() {
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
        searchCriteria.setSortBy("rating");
        searchCriteria.setSortOrder("desc");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

    }
    @Test
    public void test6_searchHotelsAndSortByٍStarRatingASC() {
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
        searchCriteria.setSortBy("stars");
        searchCriteria.setSortOrder("asc");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

    }
    @Test
    public void test7_searchHotelsAndSortByٍStarRatingDESC() {
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
        searchCriteria.setSortBy("stars");
        searchCriteria.setSortOrder("DESC");
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);

    }

    @Test
    public void test8_searchHotelsWithNoPageNumberNorPageSize() {
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
         // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);
    }

    @Test
    public void test9_searchHotelsWithNoFilters() {
        // Arrange
        HotelSearchCriteriaDTO searchCriteria = new HotelSearchCriteriaDTO();
        searchCriteria.setCity("Paris");
        searchCriteria.setCountry("France");
        searchCriteria.setCheckIn(Instant.parse("2024-01-01T00:00:00Z"));
        searchCriteria.setCheckOut(Instant.parse("2024-01-05T00:00:00Z"));
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);
    }

    @Test
    public void test10_searchHotelsWithNoCity() {
        // Arrange
        HotelSearchCriteriaDTO searchCriteria = new HotelSearchCriteriaDTO();
        searchCriteria.setCountry("France");
        searchCriteria.setCheckIn(Instant.parse("2024-01-01T00:00:00Z"));
        searchCriteria.setCheckOut(Instant.parse("2024-01-05T00:00:00Z"));
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() == 0);
    }

    @Test
    public void test11_searchHotelsWithoutCheckInAndCheckout() {
        // Arrange
        HotelSearchCriteriaDTO searchCriteria = new HotelSearchCriteriaDTO();
        searchCriteria.setCity("Paris");
        searchCriteria.setCountry("France");
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        // Act
        HotelSearchResultDTO result = searchAndFilterService.filterAndSortHotels(searchCriteria);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getHotels());
        assertTrue(result.getHotels().size() > 0);
    }

    //tests for rooms
    @Test
    public void test12_searchRooms() {
        // Arrange
        HotelIdentifierWithSearchCriteriaDTO searchCriteria = new HotelIdentifierWithSearchCriteriaDTO();
        searchCriteria.setCheckIn(Instant.parse("2024-01-01T00:00:00Z"));
        searchCriteria.setCheckOut(Instant.parse("2024-01-05T00:00:00Z"));
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        searchCriteria.setHotelId(UUID.fromString("d10a9c49-add1-44bd-98c6-2674c5b8fd78"));

        // Act
        HotelDTO result = searchAndFilterService.filterRooms(searchCriteria);

        // Assert
        assertNotNull(result);
     }


    @Test
    public void test12_searchRoomsWithoutCheckinAndCheckout() {
        // Arrange
        HotelIdentifierWithSearchCriteriaDTO searchCriteria = new HotelIdentifierWithSearchCriteriaDTO();
        searchCriteria.setNumberOfRooms(1);
        searchCriteria.setNumberOfTravelers(1);
        searchCriteria.setHotelId(UUID.fromString("d10a9c49-add1-44bd-98c6-2674c5b8fd78"));

        // Act
        HotelDTO result = searchAndFilterService.filterRooms(searchCriteria);

        // Assert
        assertNotNull(result);
     }

}
