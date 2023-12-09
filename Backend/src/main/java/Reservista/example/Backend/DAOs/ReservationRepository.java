package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.id = :reservationId AND r.user.userName = :username")
    Optional<Reservation> findReservationByIdAndUsername(@Param("reservationId") long reservationId, @Param("username") String username);


//    @Query("SELECT r FROM Reservation r WHERE r.id = :reservationId AND r.user.userName = :username AND r.checkIn > :currentTimestamp")
//    Optional<Reservation> findReservationByIdAndUsernameAndCheckInFuture(
//            @Param("reservationId") long reservationId,
//            @Param("username") String username,
//            @Param("currentTimestamp") Instant currentTimestamp
//    );

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.id = :reservationId " +
            "AND r.user.userName = :username " +
            "AND ((r.checkIn > CURRENT_TIMESTAMP AND r.isFullyRefundable = true) " +
            "OR (r.checkIn > CURRENT_TIMESTAMP.plus(5, 'DAY') AND r.isFullyRefundable = false))")
    Optional<Reservation> findReservationByIdAndUsernameAndCheckInFuture(
            @Param("reservationId") long reservationId,
            @Param("username") String username
    );
}
