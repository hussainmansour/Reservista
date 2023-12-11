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

    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms")
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacity(
            @Param("city") String city,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut,
            @Param("numberOfRooms") int numberOfRooms,
            @Param("numberOfTravelers") int numberOfTravelers,
            Pageable pageable
    );

    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms")
    Page<Hotel> findByLocation_CityAndRooms_AvailabilityDateRangeAndTotalCapacityAndPriceRangeAndStarsAndRating(
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

    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY r.roomDescription.price DESC")
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
    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY r.roomDescription.price ASC")
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
    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY h.starRating DESC")
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

    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY h.starRating ASC")
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

    //sort by rating
    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY h.rating DESC")
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

    @Query("SELECT h FROM Hotel h " +
            "LEFT JOIN h.rooms r " +
            "LEFT JOIN r.reservations res " +
            "WHERE h.location.city = :city " +
            "AND (r IS NULL OR (COALESCE(SUM(CASE WHEN res.checkOut >= :checkIn AND res.checkIn <= :checkOut THEN 0 ELSE r.roomDescription.capacity END), 0)) >= :numberOfTravelers) " +
            "AND r.roomDescription.price >= :minPrice AND r.roomDescription.price <= :maxPrice " +
            "AND h.starRating >= :minStars AND h.starRating <= :maxStars " +
            "AND h.rating >= :minRating AND h.rating <= :maxRating " +
            "GROUP BY h " +
            "HAVING COUNT(r) >= :numberOfRooms " +
            "ORDER BY h.rating ASC")
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
