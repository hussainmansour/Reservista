package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findById(Long id);

    Optional<Reservation> findByIdAndUserUserName(Long id, String username);

    @Modifying
    @Query("UPDATE Reservation r SET r.isConfirmed = true WHERE r.paymentIntentId = :paymentIntentId")
    @Transactional
    void setIsConfirmedToTrueByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);

    @Query("SELECT r.user.email, COALESCE(r.user.fullName.firstName, r.user.email) AS firstName, r.id " +
            "FROM Reservation r " +
            "WHERE r.paymentIntentId = :paymentIntentId ")
    Optional<List<Object[]>> findEmailFirstNameReservationIdByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);

    void deleteById(Long id);

    @Query("SELECT r.hotel.fullyRefundableRate " +
            "FROM Reservation r " +
            "WHERE r.id = :id")
    Integer findFullRefundableRateByReservationId(@Param("id") Long id);

    @Query("SELECT CASE WHEN r.user.userName = :userName THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.id = :reservationId")
    boolean isUsernameMatchingReservation(@Param("userName") String userName, @Param("reservationId") Long reservationId);


    @Query("SELECT r.user.email, r.hotel.name, COALESCE(r.user.fullName.firstName, r.user.email) AS firstName " +
            "FROM Reservation r " +
            "WHERE r.id = :id ")
    Optional<List<Object[]>> findEmailHotelNameFirstNameByReservationId(@Param("id") long id);

}
