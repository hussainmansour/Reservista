package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Modifying
    @Query("UPDATE Reservation r SET r.isConfirmed = true WHERE r.paymentIntentId = :paymentIntentId")
    boolean setIsConfirmedToTrueByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);

    @Query("SELECT r.user.email, COALESCE(r.user.fullName.firstName, r.user.email) AS firstName, r.id " +
            "FROM Reservation r " +
            "WHERE r.paymentIntentId = :paymentIntentId ")
    Optional<Object[]> findEmailFirstNameReservationIdByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);


}
