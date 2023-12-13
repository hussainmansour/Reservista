package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
