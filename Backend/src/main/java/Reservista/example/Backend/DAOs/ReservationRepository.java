package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findById(Long id);
}
