package Reservista.example.Backend.Services.SearchAndFilter;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DTOs.SearchAndFilter.SearchCriteriaDTO;
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

//    public HotelSearchFactory(HotelRepository hotelRepository) {
//        this.hotelRepository = hotelRepository;
//    }

    public Page<Hotel> searchHotels(SearchCriteriaDTO searchCriteria, Pageable pageable) {

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

    public Page<Hotel> sortHotelsByPrice(SearchCriteriaDTO searchCriteria, Pageable pageable) {
        if(searchCriteria.getSortOrder().equals("desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByPriceDesc(
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

    public Page<Hotel> sortHotelsByStars(SearchCriteriaDTO searchCriteria, Pageable pageable) {
        if(Objects.equals(searchCriteria.getSortOrder(), "desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByStarsDesc(
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

    public Page<Hotel> sortHotelsByRating(SearchCriteriaDTO searchCriteria, Pageable pageable) {
         if(Objects.equals(searchCriteria.getSortOrder(), "desc"))
            return hotelRepository.findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByRatingDesc(
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

    public Page<Hotel> sortHotelsByCriteria(SearchCriteriaDTO searchCriteria, Pageable pageable) {
        if ("price".equals(searchCriteria.getSortBy())) {
            return sortHotelsByPrice(searchCriteria, pageable);
        } else if ("stars".equals(searchCriteria.getSortBy())) {
            return sortHotelsByStars(searchCriteria, pageable);
        } else if ("rating".equals(searchCriteria.getSortBy())) {
            return sortHotelsByRating(searchCriteria, pageable);
        } else {
            return searchHotels(searchCriteria, pageable);
        }
    }
}

