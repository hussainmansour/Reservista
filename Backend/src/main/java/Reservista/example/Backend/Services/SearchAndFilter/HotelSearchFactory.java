package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.HotelSearchCriteriaDTO;
import Reservista.example.Backend.Models.EntityClasses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HotelSearchFactory {

    @Autowired
    private  HotelRepository hotelRepository;
    public Page<Hotel> searchHotels(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {

        return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRating(
                searchCriteria.getCountry(),
                searchCriteria.getCity(),
                searchCriteria.getCheckIn(),
                searchCriteria.getCheckOut(),
                searchCriteria.getNumberOfRooms(),
                searchCriteria.getNumberOfTravelers(),
                searchCriteria.getMinPrice(),
                searchCriteria.getMaxPrice(),
                searchCriteria.getMinStars(),
                searchCriteria.getMaxStars(),
                searchCriteria.getMinRating(),
                searchCriteria.getMaxRating(),
                pageable
        );
    }

    public Page<Hotel> sortHotelsByPrice(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {
        if(Objects.equals(searchCriteria.getSortOrder().toLowerCase(), "desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByPriceDesc(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
        else
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByPriceASC(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
    }

    public Page<Hotel> sortHotelsByStars(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {
        if(Objects.equals(searchCriteria.getSortOrder().toLowerCase(), "desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByStarsDesc(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
        else
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByStarsASC(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
    }

    public Page<Hotel> sortHotelsByRating(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {
         if(Objects.equals(searchCriteria.getSortOrder().toLowerCase(), "desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByRatingDesc(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
        else
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByRatingASC(
                    searchCriteria.getCountry(),
                    searchCriteria.getCity(),
                    searchCriteria.getCheckIn(),
                    searchCriteria.getCheckOut(),
                    searchCriteria.getNumberOfRooms(),
                    searchCriteria.getNumberOfTravelers(),
                    searchCriteria.getMinPrice(),
                    searchCriteria.getMaxPrice(),
                    searchCriteria.getMinStars(),
                    searchCriteria.getMaxStars(),
                    searchCriteria.getMinRating(),
                    searchCriteria.getMaxRating(),
                    pageable
            );
    }

    public Page<Hotel> sortHotelsByCriteria(HotelSearchCriteriaDTO searchCriteria, Pageable pageable) {
        if ("price".equalsIgnoreCase(searchCriteria.getSortBy())) {
            return sortHotelsByPrice(searchCriteria, pageable);
        } else if ("stars".equalsIgnoreCase(searchCriteria.getSortBy())) {
            return sortHotelsByStars(searchCriteria, pageable);
        } else if ("rating".equalsIgnoreCase(searchCriteria.getSortBy())) {
            return sortHotelsByRating(searchCriteria, pageable);
        } else {
            return searchHotels(searchCriteria, pageable);
        }
    }
}

