package Reservista.example.Backend.DAOs;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import Reservista.example.Backend.Models.EntityClasses.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {


    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd "+
            "LEFT JOIN rd.reservedRooms rr "+
            "LEFT JOIN rr.reservation res "+
            "WHERE h.location.city = :city "+
            "AND h.location.country = :country "+
            "AND rd.roomCount >= :numberOfRooms "+
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers "+
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice "+
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars"
     )
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRating(
            @Param("country") String country,
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );

    //sort by price

    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price DESC"
    )
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByPriceDesc(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );
    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price ASC"
    )
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByPriceASC(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );






    // TODO: need to write the Query

    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price ASC"
    )
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByStarsDesc(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );
    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price ASC"
    )
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByStarsASC(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );
    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price ASC"
    )
    //sort by rating
     Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByRatingDesc(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );
    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.roomDescriptions rd " +
            "LEFT JOIN rd.reservedRooms rr " +
            "LEFT JOIN rr.reservation res " +
            "WHERE h.location.city = :city " +
            "AND h.location.country = :country " +
            "AND rd.roomCount >= :numberOfRooms " +
            "AND rd.capacity * :numberOfRooms >= :numberOfTravelers " +
            "AND (SELECT COUNT(rr.id) FROM ReservedRoom rr WHERE rr.roomDescription.id = rd.id AND rr.reservation.checkOut >= :checkIn AND rr.reservation.checkIn <= :checkOut) <= rd.roomCount - :numberOfRooms " +
            "AND rd.price BETWEEN :minPrice AND :maxPrice " +
            "AND h.rating BETWEEN :minRating AND :maxRating " +
            "AND h.starRating BETWEEN :minStars AND :maxStars " +
            "ORDER BY rd.price ASC"
    )
     Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRatingOrderByRatingASC(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minStars") int minStars,
            @Param("maxStars") int maxStars,
            @Param("minRating") double minRating,
            @Param("maxRating") double maxRating,
            Pageable pageable
    );
}
