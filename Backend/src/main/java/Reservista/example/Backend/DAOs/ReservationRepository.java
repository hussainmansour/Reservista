package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findById(Long id);

    @Modifying
    @Query("UPDATE Reservation r SET r.isConfirmed = true WHERE r.paymentIntentId = :paymentIntentId")
    @Transactional
    void setIsConfirmedToTrueByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);

    @Query("SELECT r.user.email, COALESCE(r.user.fullName.firstName, r.user.email) AS firstName, r.id " +
            "FROM Reservation r " +
            "WHERE r.paymentIntentId = :paymentIntentId ")
    Optional<List<Object[]>> findEmailFirstNameReservationIdByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);
    void deleteById(Long id);


    // Get all upcoming reservations for a user
    @Query("SELECT r FROM Reservation r WHERE r.user.userName = :userName AND r.isConfirmed = true AND r.checkIn > :currentDate")
    Optional<List<Reservation>> findUpcomingReservationsByUserName(@Param("userName") String userName, @Param("currentDate") Instant currentDate);

    // Get all history reservations for a user
    @Query("SELECT r FROM Reservation r WHERE r.user.userName = :userName AND r.isConfirmed = true AND r.checkIn < :currentDate")
    Optional<List<Reservation>> findHistoryReservationsByUserName(@Param("userName") String userName, @Param("currentDate") Instant currentDate);

}
