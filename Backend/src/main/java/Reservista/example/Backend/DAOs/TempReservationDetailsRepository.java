package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.TempReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TempReservationDetailsRepository extends JpaRepository<TempReservationDetails, UUID> {
}
