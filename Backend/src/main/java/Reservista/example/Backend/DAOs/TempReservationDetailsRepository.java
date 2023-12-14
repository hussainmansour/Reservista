package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;
import Reservista.example.Backend.Models.EntityClasses.TempReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TempReservationDetailsRepository extends JpaRepository<TempReservationDetails, UUID> {



    @Query("SELECT trd.invoice FROM TempReservationDetails trd WHERE trd.reservation.id = :reservationId")
    Optional<String> findInvoiceByReservationId(@Param("reservationId") Long reservationId);

}
